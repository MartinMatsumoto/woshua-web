package com.woshua.structure.praxis.repository;

import com.woshua.structure.maptree.domain.MapTree;
import com.woshua.structure.praxis.domain.Praxis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Acer on 2017/3/24.
 */
public interface PraxisRepository extends PagingAndSortingRepository<Praxis, Long>, JpaSpecificationExecutor<Praxis> {

    /*// 原生的mysql 也可以用jpal 面向对象的编程
    @Query(value = "select u.* from userinfo u where u.name = ?1", nativeQuery = true)
    UserInfo findByname(String name);

    // 修改
    @Modifying
    @Query(value = "update UserInfo set name=?1 where name=?2")
    void updateUser(String newName, String oldName);

    // 删除
    @Modifying
    @Query(value = "delete from UserInfo where id=?1")where position=?1 and type=?2
    void delete(Integer id);*/

//    Page<Praxis> findAll(Specification<Praxis> params, Pageable pageable);
}
