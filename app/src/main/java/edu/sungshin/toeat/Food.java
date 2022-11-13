package edu.sungshin.toeat;

public class Food {
    String no;
    String foodName;
    String expirationDate;
    String amount;
    String market;
    String memo;

    public Food(String no, String foodName,String expirationDate,String amount,String market,String memo){
        this.no=no;
        this.foodName=foodName;
        this.expirationDate=expirationDate;
        this.amount=amount;
        this.market=market;
        this.memo=memo;
    }

    public String getName() {return foodName;}
    public void setName(String foodName){this.foodName=foodName;}

    public String getExpiration() {return expirationDate;}
    public void setExpiration(String expirationDate){this.expirationDate=expirationDate;}

    public String getNum() {return amount;}
    public void setNum(String amount){this.amount=amount;}

    public String getMarket() {return market;}
    public void setMarket(String market){this.market=market;}

    public String getMemo() {return memo;}
    public void setMemo(String memo){this.memo=memo;}

    public String getNo() {return no;}
    public void setNo(String no){this.no=no;}

}
