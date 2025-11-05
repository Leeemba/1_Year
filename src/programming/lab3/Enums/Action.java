package programming.lb3.Enums;

public enum Action {
    MAKEMEETING("начать заседание акционерного общества"),
    NOTMAKEMEET("не начать заседание акционерного общества"),
    MAKEOFFER(" сделать предложение "),
    SELL("продать"),
    NOTSELL("не продавать"),

    SEPARATE("поделить между собой");



    private final String action;

    Action(String action){
       this.action = action;
   }

    public String getAction() {
        return action;
    }

   @Override
    public String toString(){
       return  getAction().toLowerCase();
   }
}
