package com.ppqppl.stm32_server.repository;

import com.ppqppl.stm32_server.entity.Udp_temp_humi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Udp_temp_humiRepository extends JpaRepository<Udp_temp_humi,String> {
}
