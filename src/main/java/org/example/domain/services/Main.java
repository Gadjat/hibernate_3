package org.example.domain.services;

import org.example.domain.entity.City;
import org.example.domain.redis.CityCountry;
import java.util.List;

public class Main {


    public static void main(String[] args) throws Exception {
        RedisVsMySqlService redisVsMySqlService = new RedisVsMySqlService();

        List<City> allCities = redisVsMySqlService.fetchData(redisVsMySqlService);
        List<CityCountry> preparedData = redisVsMySqlService.transformData(allCities);
        redisVsMySqlService.getRedisConnectionFactory().pushToRedis(preparedData);

        redisVsMySqlService.getSessionFactory().getCurrentSession().close();

        List<Integer> ids = List.of(3, 2545, 123, 4, 189, 89, 3458, 1189, 10, 102);

        long startRedis = System.currentTimeMillis();
        redisVsMySqlService.getRedisConnectionFactory().testRedisData(ids);
        long stopRedis = System.currentTimeMillis();

        long startMysql = System.currentTimeMillis();
        redisVsMySqlService.getCityDAO().testMysqlData(ids);
        long stopMysql = System.currentTimeMillis();

        System.out.printf("%s:\t%d ms\n", "Redis", (stopRedis - startRedis));
        System.out.printf("%s:\t%d ms\n", "MySQL", (stopMysql - startMysql));

        redisVsMySqlService.shutdown();
    }


}
