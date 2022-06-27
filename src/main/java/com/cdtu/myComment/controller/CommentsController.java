package com.cdtu.myComment.controller;

import com.cdtu.myComment.entity.Comments;
import com.cdtu.myComment.service.CommentsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Comments)表控制层
 *
 * @author makejava
 * @since 2022-06-27 20:52:52
 */
@RestController
@RequestMapping("comments")
public class CommentsController {
    /**
     * 服务对象
     */
    @Resource
    private CommentsService commentsService;

    /**
     * 分页查询
     *
     * @param comments    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Comments>> queryByPage(Comments comments, PageRequest pageRequest) {
        return ResponseEntity.ok(this.commentsService.queryByPage(comments, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Comments> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.commentsService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param comments 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Comments> add(Comments comments) {
        return ResponseEntity.ok(this.commentsService.insert(comments));
    }

    /**
     * 编辑数据
     *
     * @param comments 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Comments> edit(Comments comments) {
        return ResponseEntity.ok(this.commentsService.update(comments));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.commentsService.deleteById(id));
    }

}
