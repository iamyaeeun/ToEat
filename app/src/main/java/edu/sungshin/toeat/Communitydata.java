package edu.sungshin.toeat;

public class Communitydata{
    String publisher;
    String nickname;
    String content;

    public Communitydata(String publish,String name, String contents) {
        this.publisher=publish;
        this.nickname = name;
        this.content = contents;
    }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String name) {
        nickname = name;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String contents) {
        content = contents;
    }
}

