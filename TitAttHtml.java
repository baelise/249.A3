import java.io.BufferedReader;
import java.io.FileReader;

public class TitAttHtml {
    public static void main(String[] args) {

        String htmlBeginning = """
                <!DOCTYPE html>
                <html>
                <style>
                table {font-family: arial, sans-serif;border-collapse: collapse;}
                td, th {border: 1px solid #000000;text-align: left;padding: 8px;}
                tr:nth-child(even) {background-color: #dddddd;}
                span{font-size: small}
                </style>
                <body>
                
                <table>
                <caption>""";

        String htmlAfterTitle = """
                </caption>
                """;

        //System.out.println("Please enter 2 CSV files");
        String file1Name = "C:/Users/Andrei/IdeaProjects/A3.249/src/doctorList-CSV format.csv";
        String line;
        String title = "";
        String attributes = "";
        String data = "";

        int lineNum = 0;

        try {
            FileReader fileRead = new FileReader(file1Name);
            BufferedReader inputStream = new BufferedReader(fileRead);
            while ((line = inputStream.readLine())!= null){

                if (lineNum == 0){
                    title = line;
                }

                if (lineNum == 1){
                    attributes = line;
                }
                if(lineNum > 1){
                    data += line;
                    data += "\n";
                }
                lineNum ++;
            }

        }
        catch (Exception e){
            System.out.println("wrong file name dumbo");
        }

        System.out.println(htmlBeginning + title + htmlAfterTitle);
        String[] attSplit = attributes.split(",");
        String htmlAttFormat =
                "<tr>\n" +
                    "\t<th>"+attSplit[0]+"</th>\n"+
                    "\t<th>"+attSplit[1]+ "</th>\n"+
                    "\t<th>"+attSplit[2]+"</th>\n" +
                    "\t<th>"+attSplit[3]+"</th>\n" +"</tr>\n";
        System.out.println(htmlAttFormat);
        String dataCom = data.replaceAll("\n", ",");
        String[] dataSplit = dataCom.split(",");
        String BeginningData = """
                <tr>
                    <td>""";

        //Mixes html and the data
        for (int i = 0; i < dataSplit.length-1; i++) {

            if(i % 4 == 0 && i !=0){

                BeginningData += "\n" + "</tr>" + "\n" + """
                <tr>
                    """;
            }
            if(i != 0){
                BeginningData += "\t<td>";
            }

            BeginningData += dataSplit[i];
            BeginningData += "</td>\n";

        }
        BeginningData += """
                </tr>
                </table>
                <span>""" + dataSplit[dataSplit.length-1] + "</span>" + """
                </body>
                </html>""";
      System.out.println(BeginningData);
    }
}
