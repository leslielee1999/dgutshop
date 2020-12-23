package org.dgutstu.dgutshop.admin.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dgutstu.dgutshop.core.storage.StorageService;
import org.dgutstu.dgutshop.core.util.ResponseUtil;
import org.dgutstu.dgutshop.core.validator.Order;
import org.dgutstu.dgutshop.core.validator.Sort;
import org.dgutstu.dgutshop.db.domain.DgutshopStorage;
import org.dgutstu.dgutshop.db.service.DgutshopStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 23:26 2020/12/22
 */
@RestController
@RequestMapping("/admin/storage")
@Validated
public class AdminStorageController {
    private final Log logger = LogFactory.getLog(AdminStorageController.class);


    @Autowired
    private StorageService storageService;
    @Autowired
    private DgutshopStorageService dgutshopStorageService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/list")
    public Object list(String key, String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<DgutshopStorage> storageList = dgutshopStorageService.querySelective(key, name, page, limit, sort, order);
        return ResponseUtil.okList(storageList);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/create")
    public Object create(@RequestParam("file") MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        DgutshopStorage dgutshopStorage = storageService.store(file.getInputStream(), file.getSize(),
                file.getContentType(), originalFilename);
        return ResponseUtil.ok(dgutshopStorage);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/read")
    public Object read(@NotNull Integer id) {
        DgutshopStorage storageInfo = dgutshopStorageService.findById(id);
        if (storageInfo == null) {
            return ResponseUtil.badArgumentValue();
        }
        return ResponseUtil.ok(storageInfo);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/update")
    public Object update(@RequestBody DgutshopStorage dgutshopStorage) {
        if (dgutshopStorageService.update(dgutshopStorage) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(dgutshopStorage);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/delete")
    public Object delete(@RequestBody DgutshopStorage dgutshopStorage) {
        String key = dgutshopStorage.getKey();
        if (StringUtils.isEmpty(key)) {
            return ResponseUtil.badArgument();
        }
        dgutshopStorageService.deleteByKey(key);
        storageService.delete(key);
        return ResponseUtil.ok();
    }
}
