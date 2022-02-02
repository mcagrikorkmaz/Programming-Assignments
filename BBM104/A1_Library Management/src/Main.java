import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    static ArrayList<Author> authorList = new ArrayList<>();
    static ArrayList<Article> articleList = new ArrayList<>();
    static int readArticleCount = 0;
    public static void main(String[] args) throws IOException {
        readAuthor(args[0]);
        PrintWriter outputWriter = new PrintWriter(new FileWriter("output.txt"));
        BufferedReader commandReader = new BufferedReader(new FileReader(args[1]));
        String line;
        while ((line = commandReader.readLine()) != null) {
            List<String> commandArray = new ArrayList<>(Arrays.asList(line.trim().split(" ")));
            if(commandArray.get(0).equals("read")){
                readArticle(commandArray.get(1));
            }
            else if(commandArray.get(0).equals("completeAll")){
                completeAll();
                outputWriter.println("*************************************completeAll" +
                        " Successful*************************************");
            }
            else if(commandArray.get(0).equals("sortedAll")){
                sortedAll();
                outputWriter.println("*************************************sortedAll" +
                        " Successful*************************************");
            }
            else if(commandArray.get(0).equals("del")){
                delMethod(commandArray.get(1));
                outputWriter.println("*************************************del" +
                        " Successful*************************************");
            }
            else{
                outputWriter.println("----------------------------------------------List" +
                        "---------------------------------------------");
                for(Author author: authorList){
                    outputWriter.println(author.getFirstLine());
                    for(String articleLine: author.getOutputList()){
                        outputWriter.println(articleLine);
                    }
                    outputWriter.println();
                }
                outputWriter.println("----------------------------------------------End" +
                        "----------------------------------------------");
            }

        }
        commandReader.close();
        outputWriter.close();

    }
    public static void completeAll() throws IOException {
        for(Author author:authorList){
            for(Article article: articleList){
                if(article.getId().substring(0,3).equals(author.getId())&&!author.getAllArticleList().contains(article.getId())){
                    author.setArticle(article.getFullID());
                    author.setAllArticleList(article.getId());
                }
            }
        }
    }
    public static void sortedAll(){
        for(Author author:authorList){
            author.sorted();
        }
    }

    public static void readAuthor(String authorTxt) throws IOException {
        BufferedReader authorReader = new BufferedReader(new FileReader(authorTxt));
        String line;
        int index = 0;
        while ((line = authorReader.readLine()) != null) {
            List<String> authorArray = new ArrayList<>(Arrays.asList(line.trim().split(" ")));
            if(authorArray.size()==2){
                authorList.add(index,new Author(authorArray.get(1)));
            }
            else if(authorArray.size()==3){
                authorList.add(index,new Author(authorArray.get(1),authorArray.get(2)));
            }
            else if(authorArray.size()==4){
                authorList.add(index,new Author(authorArray.get(1),authorArray.get(2),authorArray.get(3)));
            }
            else if(authorArray.size()==5){
                authorList.add(index,new Author(authorArray.get(1),authorArray.get(2),authorArray.get(3),authorArray.get(4)));
            }
            else{
                authorList.add(index,new Author(authorArray.get(1),authorArray.get(2),authorArray.get(3),authorArray.get(4),authorArray.get(5)));
            }
            for(int i=6 ;i<authorArray.size();i++){
                authorList.get(index).setAllArticleList(authorArray.get(i));
            }
            index++;
        }

    }
    public static void readArticle(String articleTxt) throws IOException{
        BufferedReader articleReader = new BufferedReader(new FileReader(articleTxt));
        String line;
        int index = 0;
        while ((line = articleReader.readLine()) != null) {
            String newline = line.replace("ARTICLE ","+");
            ArrayList<String> articleArray = new ArrayList<>(Arrays.asList(line.trim().split(" ")));
            articleList.add(index,new Article(articleArray.get(1),articleArray.get(2),articleArray.get(3),articleArray.get(4),newline));
        }
        Collections.reverse(articleList);
        if(readArticleCount==0){
            for(Author author:authorList){
                for(String articleID: author.getAllArticleList()){
                    for(Article article: articleList){
                        if(articleID.equals(article.getId())){
                            author.setArticle(article.getFullID());
                        }
                    }
                }

            }
        }
        readArticleCount++;
    }
    public static void delMethod(String authorID){
        authorList.removeIf(deletedAuthor -> deletedAuthor.getId().equals(authorID));
    }
}
