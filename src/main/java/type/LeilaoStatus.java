package type;

public enum LeilaoStatus {

    EM_ABERTO("Em aberto"),
    EM_ANDAMENTO("Em andamento"),
    FECHADO("Fechado");


    private String status;

    LeilaoStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}