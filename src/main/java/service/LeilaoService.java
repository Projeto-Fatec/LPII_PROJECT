package service;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import database.BaseDados;
import model.InstituicaoFinanceira;
import model.Leilao;
import type.LeilaoStatus;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.TaskScheduler;
import jakarta.inject.Inject;
import jakarta.inject.Named;

public class LeilaoService {

    @Inject
    private BaseDados baseDados;

    @Inject
    private InstituicaoFinanceiraService instituicaoFinanceiraService;

    private TaskScheduler taskScheduler;

    public LeilaoService(@Named(TaskExecutors.SCHEDULED) TaskScheduler taskScheduler){
        this.taskScheduler = taskScheduler;
    }

    public List<Leilao> list(){
        return baseDados.findAllLeiloes();
    }

    public Leilao get(Leilao leilao){
        return baseDados.findLeilaoById(leilao.getId());
    }

    public Leilao set(Leilao leilao){
        Date now = new Date();

        InstituicaoFinanceira instituicaoFinanceira = instituicaoFinanceiraService.get(leilao.getInstituicaoFinanceira());
        if(instituicaoFinanceira == null)
            return null;

        if(now.after(leilao.getDataInicial()) || leilao.getDataInicial().after(leilao.getDataFinal()))
            return null;

        leilao.setInstituicaoFinanceira(instituicaoFinanceira);
        leilao.setLeilaoStatus(LeilaoStatus.EM_ABERTO);

        leilao = baseDados.saveLeilao(leilao);

        taskScheduler.schedule(Duration.between(now.toInstant(), leilao.getDataInicial().toInstant()), this::updateLeiloesStatus);
        taskScheduler.schedule(Duration.between(now.toInstant(), leilao.getDataFinal().toInstant()), this::updateLeiloesStatus);

        return leilao;
    }

    public Leilao remove(Leilao leilao){
        if(leilao.getLeilaoStatus() == LeilaoStatus.EM_ANDAMENTO || leilao.getLeilaoStatus() == LeilaoStatus.FECHADO)
            return null;

        return baseDados.deleteLeilaoById(leilao.getId());
    }

    private void updateLeiloesStatus(){
        Date now = new Date();
        System.out.println("qualquer coisa");
        for(Leilao leilao: baseDados.findAllLeiloes()){
            if(now.after(leilao.getDataInicial())){
                if(now.after(leilao.getDataFinal())){
                    leilao.setLeilaoStatus(LeilaoStatus.FECHADO);
                } else {
                    leilao.setLeilaoStatus(LeilaoStatus.EM_ANDAMENTO);
                }
            }
        }
    }
}