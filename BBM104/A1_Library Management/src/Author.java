import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Author {
    private String firstLine;
    private String id;
    private String name;
    private String university;
    private String department;
    private String email;
    private ArrayList<String> allArticleList = new ArrayList<>();
    private String articleOne;
    private String articleTwo;
    private String articleThree;
    private String articleFour;
    private String articleFive;
    private ArrayList<String> outputList = new ArrayList<>();

    public Author(String id){
        this(id,"","","","");
    }
    public Author(String id,String name){
        this(id,name,"","","");
    }
    public Author(String id, String name, String university){
        this(id,name,university,"","");
    }
    public Author(String id, String name, String university, String department){
        this(id,name,university,department,"");
    }
    public Author(String id, String name, String university, String department, String email){
        this.id = id;
        this.name = name;
        this.university = university;
        this.department = department;
        this.email = email;
        this.firstLine = "Author:"+id+"\t"+name+"\t"+university+"\t"+department+"\t"+email;
    }
    public void setAllArticleList(String articleID){
        this.allArticleList.add(articleID);
    }
    public void setArticle(String fullID) throws IOException {
        if(this.articleOne == null){
            this.articleOne = fullID;
            newArticle(this.articleOne);
        }
        else if(this.articleTwo == null){
            this.articleTwo = fullID;
            newArticle(this.articleTwo);
        }
        else if(this.articleThree == null){
            this.articleThree = fullID;
            newArticle(this.articleThree);
        }
        else if(this.articleFour == null){
            this.articleFour = fullID;
            newArticle(this.articleFour);
        }
        else if(this.articleFive == null){
            this.articleFive = fullID;
            newArticle(this.articleFive);
        }

    }
    public void newArticle(String article){
        List<String> partList = new ArrayList<>(Arrays.asList(article.trim().split(" ")));
        article = "";
        for(String part: partList){
            if(partList.indexOf(part) == partList.size()-1) article += part;
            else{
                article += part;
                article += "\t";
            }
        }
        outputList.add(article);

    }
    public void sorted(){
        Collections.sort(outputList);
    }

    public List<String> getAllArticleList() {
        return allArticleList;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUniversity() {
        return university;
    }

    public String getDepartment() {
        return department;
    }

    public String getEmail() {
        return email;
    }

    public String getArticleOne() {
        return articleOne;
    }

    public String getArticleTwo() {
        return articleTwo;
    }

    public String getArticleThree() {
        return articleThree;
    }

    public String getArticleFour() {
        return articleFour;
    }

    public String getArticleFive() {
        return articleFive;
    }

    public List<String> getOutputList() {
        return outputList;
    }

    public String getFirstLine() {
        return firstLine;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setArticleOne(String articleOne) {
        this.articleOne = articleOne;
    }

    public void setArticleTwo(String articleTwo) {
        this.articleTwo = articleTwo;
    }

    public void setArticleThree(String articleThree) {
        this.articleThree = articleThree;
    }

    public void setArticleFour(String articleFour) {
        this.articleFour = articleFour;
    }

    public void setArticleFive(String articleFive) {
        this.articleFive = articleFive;
    }
}
