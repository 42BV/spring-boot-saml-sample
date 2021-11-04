package nl._42.samldemo;

import nl._42.boot.onelogin.saml.Saml2Response;
import nl._42.boot.onelogin.saml.user.Saml2Authentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class GreetingController {

    @GetMapping("/")
    public String greeting(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("name", authentication.getName());
        model.addAttribute("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(", ")));

        Map<String, String> attributes = new HashMap<>();
        if (authentication instanceof Saml2Authentication) {
            Saml2Response response = ((Saml2Authentication) authentication).getResponse();

            Collection<String> names = response.getAttributes();
            names.forEach(name -> attributes.put(name, response.getValues(name).stream().collect(Collectors.joining(", "))));
        }
        model.addAttribute("attributes", attributes);

        return "greeting";
    }

}