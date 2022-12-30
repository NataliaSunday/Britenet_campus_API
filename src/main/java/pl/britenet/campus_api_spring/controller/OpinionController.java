package pl.britenet.campus_api_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus_api.model.Opinion;
import pl.britenet.campus_api.service.tableService.OpinionService;
import pl.britenet.campus_api_spring.service.AuthService;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/v1/opinion")
public class OpinionController {

    private final OpinionService opinionService;
    public  final AuthService authService;

    @Autowired
    public OpinionController(OpinionService opinionService, AuthService authService){
        this.opinionService = opinionService;
        this.authService = authService;
    }

    @GetMapping
    public List <Opinion> getOpinionAll(){
        return opinionService.getOpinionAll();
    }

    @GetMapping("/{opinionId}")
    public Opinion getOpinionOne(@PathVariable int opinionId){
        return this.opinionService.getOpinionOne(opinionId);
    }

    @PostMapping
    public Opinion insertOpinion(@RequestBody Opinion opinion, @RequestHeader ("Authorization") String token){
        int userId = this.authService.getUserId(token);
        opinion.setIdUser(userId);
        this.opinionService.insertOpinion(opinion);

        return  opinion;
    }

    @PutMapping
    public void updateOpinion(@RequestBody Opinion opinion){
        this.opinionService.insertOpinion(opinion);
    }

    @DeleteMapping("/{opinionId}")
    public void delOpinion(@PathVariable int opinionId) {
        opinionService.delOpinion(opinionId);
    }

    @GetMapping("/prodId/{prodId}")
    public List <Opinion> getOpinionsByProd(@PathVariable int prodId){
        return opinionService.getOpinionsByProd(prodId);
    }

}
