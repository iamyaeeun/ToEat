package edu.sungshin.toeat;

public class Communitydata{
    String nickname;
    String content;

    public Communitydata(String name, String contents) {
        this.nickname = name;
        this.content = contents;
    }

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

