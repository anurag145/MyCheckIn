package com.github.anurag145.mycheckin;



public class UserInfo {
    public  String Username;
    public  String UserID;
    public  String Date;
    public static UserInfo singleton=new UserInfo();
    public static UserInfo getSingleton() {
        return singleton;
    }
    public String s = "<html>\n" +
            "<head>\n" +
            "<style>\n" +
            ".loader,\n" +
            ".loader:before,\n" +
            ".loader:after {\n" +
            "  background: #41c4f4;\n" +
            "  -webkit-animation: load1 1s infinite ease-in-out;\n" +
            "  animation: load1 1s infinite ease-in-out;\n" +
            "  width: 1em;\n" +
            "  height: 4em;\n" +
            "}\n" +
            ".loader {\n" +
            "  color: #41c4f4;\n" +
            "  text-indent: -9999em;\n" +
            "  margin: 88px auto;\n" +
            "  position: relative;\n" +
            "  font-size: 11px;\n" +
            "  -webkit-transform: translateZ(0);\n" +
            "  -ms-transform: translateZ(0);\n" +
            "  transform: translateZ(0);\n" +
            "  -webkit-animation-delay: -0.16s;\n" +
            "  animation-delay: -0.16s;\n" +
            "}\n" +
            ".loader:before,\n" +
            ".loader:after {\n" +
            "  position: absolute;\n" +
            "  top: 0;\n" +
            "  content: '';\n" +
            "}\n" +
            ".loader:before {\n" +
            "  left: -1.5em;\n" +
            "  -webkit-animation-delay: -0.32s;\n" +
            "  animation-delay: -0.32s;\n" +
            "}\n" +
            ".loader:after {\n" +
            "  left: 1.5em;\n" +
            "}\n" +
            "@-webkit-keyframes load1 {\n" +
            "  0%,\n" +
            "  80%,\n" +
            "  100% {\n" +
            "    box-shadow: 0 0;\n" +
            "    height: 4em;\n" +
            "  }\n" +
            "  40% {\n" +
            "    box-shadow: 0 -2em;\n" +
            "    height: 5em;\n" +
            "  }\n" +
            "}\n" +
            "@keyframes load1 {\n" +
            "  0%,\n" +
            "  80%,\n" +
            "  100% {\n" +
            "    box-shadow: 0 0;\n" +
            "    height: 4em;\n" +
            "  }\n" +
            "  40% {\n" +
            "    box-shadow: 0 -2em;\n" +
            "    height: 5em;\n" +
            "  }\n" +
            "}</style>\n" +
            "</head>\n" +
            "<body>\n" +
            "<div class=\"loader\">Loading...</div>\n" +
            "</body>\n" +
            "</html>";
}
