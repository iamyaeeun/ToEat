package edu.sungshin.toeat;

public class Communitydata {
    String Nickname;
    String Content;

    public Communitydata(String nickname, String content) {
        Nickname = nickname;
        Content = content;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
