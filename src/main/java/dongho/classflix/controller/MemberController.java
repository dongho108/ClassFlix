package dongho.classflix.controller;

import dongho.classflix.domain.Gender;
import dongho.classflix.domain.Member;
import dongho.classflix.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ModelAttribute("genderTypes")
    public Gender[] genderTypes() {
        return Gender.values();
    }

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/memberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "members/memberForm";
        }
        Member member = new Member(form.getName(), form.getAge(), form.getGender(), form.getCareer());
        memberService.join(member);
        return "redirect:/";
    }
}
