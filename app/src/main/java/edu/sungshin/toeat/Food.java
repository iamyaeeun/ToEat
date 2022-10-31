package edu.sungshin.toeat;

public class Food {
    String name;
    String expiration;
    String num;
    String market;
    String memo;

    public Food(String name,String expiration,String num,String market,String memo){
        this.name=name;
        this.expiration=expiration;
        this.num=num;
        this.market=market;
        this.memo=memo;
    }

    public String getName() {return name;}
    public void setName(String name){this.name=name;}

    public String getExpiration() {return expiration;}
    public void setExpiration(String expiration){this.expiration=expiration;}

    public String getNum() {return num;}
    public void setNum(String num){this.num=num;}

    public String getMarket() {return market;}
    public void setMarket(String market){this.market=market;}

    public String getMemo() {return memo;}
    public void setMemo(String memo){this.memo=memo;}

}
