/*
 * 文件名：JsonUtil.java
 * 版权：Copyright 2007-2015 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： JsonUtil.java
 * 修改人：hongjian
 * 修改时间：2015年12月16日
 * 修改内容：新增
 */
package javaFXClient;

/**
 * 该类提供格式化JSON字符串的方法。 该类的方法formatJson将JSON字符串格式化，方便查看JSON数据。
 * <p>
 * 例如：
 * <p>
 * JSON字符串：["yht","xzj","zwy"]
 * <p>
 * 格式化为：
 * <p>
 * [
 * <p>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"yht",
 * <p>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"xzj",
 * <p>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"zwy"
 * <p>
 * ]
 * 
 * <p>
 * 使用算法如下：
 * <p>
 * 对输入字符串，追个字符的遍历
 * <p>
 * 1、获取当前字符。
 * <p>
 * 2、如果当前字符是前方括号、前花括号做如下处理：
 * <p>
 * （1）如果前面还有字符，并且字符为“：”，打印：换行和缩进字符字符串。
 * <p>
 * （2）打印：当前字符。
 * <p>
 * （3）前方括号、前花括号，的后面必须换行。打印：换行。
 * <p>
 * （4）每出现一次前方括号、前花括号；缩进次数增加一次。打印：新行缩进。
 * <p>
 * （5）进行下一次循环。
 * <p>
 * 3、如果当前字符是后方括号、后花括号做如下处理：
 * <p>
 * （1）后方括号、后花括号，的前面必须换行。打印：换行。
 * <p>
 * （2）每出现一次后方括号、后花括号；缩进次数减少一次。打印：缩进。
 * <p>
 * （3）打印：当前字符。
 * <p>
 * （4）如果当前字符后面还有字符，并且字符不为“，”，打印：换行。
 * <p>
 * （5）继续下一次循环。
 * <p>
 * 4、如果当前字符是逗号。逗号后面换行，并缩进，不改变缩进次数。
 * <p>
 * 5、打印：当前字符。
 * 
 * @author yanghaitao
 * @version [版本号, 2014年9月29日]
 */
public class JsonUtil { /**
     * 打印输入到控制台
     * @param jsonStr
     * @author   lizhgb
     * @Date   2015-10-14 下午1:17:22
     */
    public static void printJson(String jsonStr){
        System.out.println(formatJson(jsonStr));
    }
    
    /**
     * 格式化
     * @param jsonStr
     * @return
     * @author   lizhgb
     * @Date   2015-10-14 下午1:17:35
     */
    public static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr)) return "";
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                case '{':
                case '[':
                    sb.append(current);
                    sb.append('\n');
                    indent++;
                    addIndentBlank(sb, indent);
                    break;
                case '}':
                case ']':
                    sb.append('\n');
                    indent--;
                    addIndentBlank(sb, indent);
                    sb.append(current);
                    break;
                case ',':
                    sb.append(current);
                    if (last != '\\') {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }
 
        return sb.toString();
    }
 
    /**
     * 添加space
     * @param sb
     * @param indent
     * @author   lizhgb
     * @Date   2015-10-14 上午10:38:04
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }}