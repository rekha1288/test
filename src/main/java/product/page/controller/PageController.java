package product.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import product.page.entity.PageEntity;
import product.page.service.PageService;

@Controller
public class PageController {
    public final PageService pageService;

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping("test")
    public String method1(Model model){
        model.addAttribute("pageEntity",new PageEntity());
        return "index";
    }
}
