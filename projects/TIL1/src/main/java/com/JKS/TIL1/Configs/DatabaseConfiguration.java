package com.JKS.TIL1.Configs;


import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import lombok.extern.slf4j.Slf4j;


// DAO가 되는 Mapper의 경로를 설정
// DB연결정보가 담겨있는 application.properties를 참조하도록 설정
@Configuration
@MapperScan(basePackages="com.JKS.TIL1.DAO", sqlSessionFactoryRef = "sqlSessionFactory")
@PropertySource("classpath:application.properties")
public class DatabaseConfiguration {
    
    // 프로젝트의 환경정보를 담고있는 applicationContext사용
    @Autowired
    private ApplicationContext applicationContext;

    // 기본적으로 hikari를 사용할 수 있다.
    // application.properties에서 spring.datasource.hikari.* 로 선언된 정보를 불러와 bean으로 등록
    @Bean
    @ConfigurationProperties(prefix="spring.datasource.hikari")
    public HikariConfig hikariConfig(){
        return new HikariConfig();
    }

    // dataSource를 hikariConfig로 생성한 객체를 사용
    @Bean(destroyMethod="close", name="dataSource")
    public DataSource dataSource() {
        DataSource dataSource = new HikariDataSource(hikariConfig());

        return dataSource;
    }

    @Bean(name="sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource")DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // dataSource를 앞서 bean으로 선언한 hikariConfig를 사용 (DB연결정보)
        sqlSessionFactoryBean.setDataSource(dataSource);
        // sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml"));

        // resources경로의 xml로 정의한 sql mapper 경로설정
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/**/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name="sqlSession")
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    

}