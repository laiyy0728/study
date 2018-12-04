package com.laiyy.cloud_java.mock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author laiyy
 * @date 2018/11/30 17:24
 * @description
 *
 * 注解 @RepositoryRestResource 声明为一个 REST 风格的 repository
 */
@RepositoryRestResource
public interface CatRepository extends JpaRepository<Cat, Integer> {
}
