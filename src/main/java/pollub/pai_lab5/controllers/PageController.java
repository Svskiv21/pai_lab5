package pollub.pai_lab5.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pollub.pai_lab5.entities.Zadanie;
import pollub.pai_lab5.repositories.ZadanieRepository;

@Controller
public class PageController {

    public ZadanieRepository zadanieRepository;

    public PageController(ZadanieRepository zadanieRepository) {
        this.zadanieRepository = zadanieRepository;
    }

    @RequestMapping("/")
    @ResponseBody
    public String mainPage(){
        return "Hello Spring Boot from mainPage() method!";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String pageTwo(){
        return "Hello Spring Boot from pageTwo() method!";
    }

    @RequestMapping("/listaZadan")
    @ResponseBody
    public String listaZadan() {
        StringBuilder odp = new StringBuilder();
        Zadanie zadanie = new Zadanie("Zadanie", "Zadanie do wykonania", 2000.0);
        //korzystając z obiektu repozytorium zapisujemy zadanie do bazy
        zadanieRepository.save(zadanie);
        //korzystając z repozytorium pobieramy wszystkie zadania z bazy
        for(Zadanie i: zadanieRepository.findAll()) {
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }

}
