package com.guy.carpenter.zipper.zipdecoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guy.carpenter.zipper.zipdecoder.entity.ZipDetail;

@Repository
public interface ZipRepository extends JpaRepository<ZipDetail, Integer>{

}
