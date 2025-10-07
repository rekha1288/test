package product.page.service;

import org.springframework.stereotype.Service;
import product.page.entity.PageEntity;
import product.page.repository.PageRepo;

@Service
public class PageService {
    public final PageRepo pageRepo;

    public PageService(PageRepo pageRepo) {
        this.pageRepo = pageRepo;
    }

    public PageEntity saveMethod(PageEntity pageEntity){
        return pageRepo.save(pageEntity);
    }
}
