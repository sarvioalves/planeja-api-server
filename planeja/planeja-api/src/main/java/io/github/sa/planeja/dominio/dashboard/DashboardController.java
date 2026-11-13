package io.github.sa.planeja.dominio.dashboard;

import io.github.sa.planeja.dominio.dashboard.dto.Dashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dashboard")
public class DashboardController {

    @Autowired
    private DashboardService service;

    @GetMapping
    public Dashboard obterDashBoardMesAtual(){
        return service.obterDashBoardMesAtual();
    }
}
