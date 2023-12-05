package pollub.pai_lab5.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    public String mainPage() {
        return "Hello Spring Boot from mainPage() method!";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String pageTwo() {
        return "Hello Spring Boot from pageTwo() method!";
    }

    @RequestMapping("/listaZadan")
    @ResponseBody
    public String listaZadan() {
        StringBuilder odp = new StringBuilder();
        Zadanie zadanie = new Zadanie("Zadanie", "Zadanie do wykonania", 2000.0);
        zadanieRepository.save(zadanie);
        wiecejZadan(); // wygenerowanie kolejnych zadań do bazy
        for (Zadanie i : zadanieRepository.findAll()) {
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public void deleteZadanie(@PathVariable Long id) {
        try {
            zadanieRepository.deleteById(id);
        } catch (Exception e) {
            throw new NullPointerException("nie istnieje");
        }

//        zadanieRepository.deleteById(id);
    }

    @GetMapping("/wykonaneZadania")
    @ResponseBody
    public String listaWykonanychZadan() throws Exception { // zabezpieczyc przed nullem
        StringBuilder odp = new StringBuilder();
//        try {
//            List<Zadanie> wykonaneZadania = zadanieRepository.findAllByWykonane(true);
//            for (Zadanie z : wykonaneZadania) {
//                odp.append(z).append("<br>");
//            }
//            return odp.toString();
//        } catch (Exception e) {
//            throw new Exception("Ni ma nic");
//        }
        for (Zadanie z : zadanieRepository.findAllByWykonane(true)) {
            odp.append(z).append("<br>");
        }
        return odp.toString();
    }

    @GetMapping("/koszt-mniejszy-niz/{koszt}")
    @ResponseBody
    public String findZadaniaWithKosztLessThan(@PathVariable double koszt) {
        StringBuilder odp = new StringBuilder();
        for (Zadanie z : zadanieRepository.findByKosztLessThan(koszt)) {
            odp.append(z).append("<br>");
        }
        return odp.toString();
    }

    @GetMapping("/kosz-pomiedzy/{min}/{max}")
    @ResponseBody
    public String findZadaniaWithKosztBetween(@PathVariable double min, @PathVariable double max) {
        StringBuilder odp = new StringBuilder();
        for (Zadanie z : zadanieRepository.findByKosztBetween(min, max)) {
            odp.append(z).append("<br>");
        }
        return odp.toString();
    }

    private void wiecejZadan() {
        double k = 1000;
        boolean wyk = true;
        for (int i = 1; i <= 10; i++) {
            Zadanie z = new Zadanie();
            z.setNazwa("zadanie " + i);
            z.setOpis("Opis czynnosci do wykonania w zadaniu " + i);
            z.setKoszt(k);
            z.setWykonane(wyk);
            k += 200.50;
            zadanieRepository.save(z);
        }
    }
}
