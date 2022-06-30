package com.cdtu.myComment.service.impl;

import com.cdtu.myComment.entity.Comments;
import com.cdtu.myComment.dao.CommentsDao;
import com.cdtu.myComment.service.CommentsService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * (Comments)表服务实现类
 *
 * @author makejava
 * @since 2022-06-29 14:03:17
 */
@Service("commentsService")
public class CommentsServiceImpl implements CommentsService {
    @Resource
    private CommentsDao commentsDao;


    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    @Override
    public Comments queryById(Integer userid) {
        return this.commentsDao.queryById(userid);
    }

    /**
     * 分页查询
     *
     * @param comments    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Comments> queryByPage(Comments comments, PageRequest pageRequest) {
        long total = this.commentsDao.count(comments);
        return new PageImpl<>(this.commentsDao.queryAllByLimit(comments, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param comments 实例对象
     * @return 实例对象
     */
    @Override
    public Comments insert(Comments comments) {
        this.commentsDao.insert(comments);
        return comments;
    }

    /**
     * 修改数据
     *
     * @param comments 实例对象
     * @return 实例对象
     */
    @Override
    public Comments update(Comments comments) {
        this.commentsDao.update(comments);
        return this.queryById(comments.getUserid());
    }

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer userid) {
        return this.commentsDao.deleteById(userid) > 0;
    }

    @Override
    public List<Comments> getByShopId(Integer shopId) {
        return commentsDao.getByShopId(shopId);
    }

    @Override
    public HashMap<String, Object> upload(MultipartFile[] files, Integer shopId, HttpSession session){
        String username = "";
        HashMap<String, Object> map = new HashMap<>();
        System.out.println(session.getAttribute("username"));
        if (session.getAttribute("username") != null){
            username = (String) session.getAttribute("username");
        }else{
            map.put("error","用户未登录");
            return map;
        }
        String realPath = "src\\main\\resources\\static\\img\\user\\"+username+"\\"+shopId;
        File folder = new File(realPath);
        if (!folder.exists() && !folder.isDirectory()) {
            folder.mkdirs();
        }
        if (files != null && files.length > 0){
            for (int i =0;i <files.length;i++){
                MultipartFile file = files[i];
                if (!file.isEmpty()){
                    int a = i;
                    File newFile = new File(realPath+"/reply"+a+".jpg").getAbsoluteFile();
                    while (newFile.exists()){
                        a++;
                        newFile = new File(realPath+"/reply"+a+".jpg").getAbsoluteFile();
                    }
                    try {
                        file.transferTo(newFile);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        map.put("code","200");
        return map;
    }
}
