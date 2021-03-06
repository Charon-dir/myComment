package com.cdtu.myComment.controller;

import cn.hutool.json.JSONArray;
import com.cdtu.myComment.entity.Shop;
import com.cdtu.myComment.service.ShopService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * (Shop)表控制层
 *
 * @author makejava
 * @since 2022-06-28 22:15:09
 */
@RestController
@RequestMapping("shop")
public class ShopController {
    /**
     * 服务对象
     */
    @Resource
    private ShopService shopService;

    @PostMapping("/shopShow")
    public List<Shop> shopShow(){
        return shopService.getAll();
    }
    @PostMapping("/getShopAll")
    public List<HashMap<String,Object>> getShopById(@RequestParam(name = "id") Integer id){
        return shopService.getShopById(id);
    }

    /**
     * 分页查询
     *
     * @param shop        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Shop>> queryByPage(Shop shop, PageRequest pageRequest) {
        return ResponseEntity.ok(this.shopService.queryByPage(shop, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Shop> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.shopService.queryById(id));
    }


    @PostMapping
    public Shop add(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name")  String name,
            @RequestParam("typeId") String typeId,
            @RequestParam("address") String address,
            @RequestParam("introduction") String introduction
            ) throws IOException {
        return this.shopService.insert(file,name,typeId,address,introduction);
    }

    /**
     * 编辑数据
     *
     * @param shop 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Shop> edit(Shop shop) {
        return ResponseEntity.ok(this.shopService.update(shop));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.shopService.deleteById(id));
    }

    @PostMapping("/search")
    public  List<Shop> search(@RequestParam(name = "name") String name) {
        System.out.printf("----------------------" + name + "-------------------------");
        return shopService.search(name);
    }

        @PostMapping("/classifySelect")
        public  List<Shop>  classify(@RequestParam(name = "typeid") String typeid){
            System.out.println(typeid);
            return shopService.classifySelect(typeid);
        }

}

