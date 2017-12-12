package com.picc.repository.targetplan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.picc.entity.targetplan.Userinfo;

public interface UserInfoRepository extends JpaRepository<Userinfo, Integer>{

}
