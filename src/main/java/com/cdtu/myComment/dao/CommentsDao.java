package com.cdtu.myComment.dao;

import com.cdtu.myComment.entity.Comments;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (Comments)表数据库访问层
 *
 * @author makejava
 * @since 2022-06-27 20:52:53
 */
public interface CommentsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    Comments queryById(Integer userid);

    /**
     * 查询指定行数据
     *
     * @param comments 查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Comments> queryAllByLimit(Comments comments, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param comments 查询条件
     * @return 总行数
     */
    long count(Comments comments);

    /**
     * 新增数据
     *
     * @param comments 实例对象
     * @return 影响行数
     */
    int insert(Comments comments);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Comments> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Comments> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Comments> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Comments> entities);

    /**
     * 修改数据
     *
     * @param comments 实例对象
     * @return 影响行数
     */
    int update(Comments comments);

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 影响行数
     */
    int deleteById(Integer userid);

}
