package Inlämningsuppgift;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/*
Inlämningsuppgit i kursen Funktionell Programmering för JAVA-programmet
För samtliga funktioner i denna fil, byt ut "throw UnSupportedException"-raden
och skriv ett pipeline-uttryck som returnerar det som ska returneras.
Streams MÅSTE användas i samtliga funktioner som lämnas in
För att testa om era funktioner funkar, kör testerna som hör till denna fil
För att bli godkänd på denna uppgift måste minst 7 av funktionerna vara implementerade.
Sigruns bedömning av denna uppgift kommer att gå till så att hon klipper in er version av denna fil
i sitt projekt och kör testerna.
Hennes kontroll om ni har klarat uppgifterna eller inte görs genom att
hon kollar att tillräckeligt många av tester går gröna. Pga detta ska ni inte ändra i någon fil
medföljande detta projekt, och inte heller metodsignaturerna i denna fil eller era tester, eftersom
ni då riskerar att påverka rättningen i negativ riktning.
 */

public class RewriteMe {
    
    public Database database = new Database();
    public List<Question> questions = database.getQuestions();
    private boolean add;
    
    //Skriv en funktioner som returnerar hur många frågor det finns i databasen?
    public int getAmountOfQuestionsInDatabase()
    {
        int result;
    
        result = (int) questions.stream()
            .count();
        
        return result;
        
    }
    
    //Hur många frågor finns i databasen för en viss, given kategori (som ges som inparameter)
    public int getAmountOfQuestionsForACertainCategory(Category category)
    {
        int result;
    
        result = (int) questions.stream()
            .filter(questions -> questions.getCategory().equals(category))
            .count();
    
        return result;
    
    }

    //Skapa en lista innehållandes samtliga frågesträngar i databasen
    public List<String> getListOfAllQuestions()
    {
        List <String> result;
        int fields;
    
        fields = (int) questions.stream()
            .count();
    
        result = new ArrayList<String>(fields);
        
        questions.stream()
            .forEach(questions -> result.add(questions.getQuestionString()));
        
        return result;
        
    }
    
    //Skapa en lista innehållandes samtliga frågesträngar där frågan tillhör en viss kategori
    //Kategorin ges som inparameter
    public List<String> getAllQuestionStringsBelongingACategory(Category category)
    {
        List <String> result;
        int fields;
    
        fields = (int) questions.stream()
            .filter(questions -> questions.getCategory().equals(category))
            .count();
    
        result = new ArrayList<String>(fields);
    
        questions.stream()
            .filter(questions -> questions.getCategory().equals(category))
            .forEach(questions -> result.add(questions.getQuestionString()));
    
        return result;

    }

    //Skapa en lista av alla svarsalternativ, där varje svarsalternativ får förekomma
    // en och endast en gång i den lista som du ska returnera
    public List<String> getAllAnswerOptionsDistinct()
    {
        List <String> resultHolder, resultFinal;
    
        List <List> answersList;
    
        AtomicInteger questionsPerListAI;
    
        int listsWithQuestions, questionsPerListInt, allQuestions, allDistinctQuestions, laps, checks, finalIndex;
    
    
        answersList = new ArrayList <List>();
        questionsPerListAI = new AtomicInteger();
        resultFinal = new ArrayList <String>();
    
    
        questions.stream().forEach(question -> answersList.add(question.getAllAnswers()));
    
    
        listsWithQuestions = answersList.size();
    
    
        questions.stream().forEach(question -> questionsPerListAI.set(question.getAllAnswers().size()));
    
    
        questionsPerListInt = questionsPerListAI.get();
        allQuestions = questionsPerListInt * listsWithQuestions;
    
    
        resultHolder = new ArrayList <String>(allQuestions);
    
    
        for(int i = 0; i < listsWithQuestions; i++)
        {
            int finalI = i;
        
            answersList.stream().distinct().forEach(question -> answersList.get(finalI).toArray(new String[questionsPerListInt]));
        
        }
    
    
        laps = 0;
        for(int i1 = 0; i1 < listsWithQuestions; i1++)
        {
            for(int i2 = 0; i2 < questionsPerListInt; i2++)
            {
                int finalI1 = i1, finalI2 = i2, finalLaps = laps;
            
            
                answersList.stream().distinct().forEach(question -> resultHolder.add(finalLaps , (String) answersList.get(finalI1).get(finalI2)));
            
            
                ++laps;
            
            }
        
        }
    
    
        resultHolder.stream()
        .distinct()
        .sorted()
        .forEach(questions -> resultFinal.add(questions));;
    
    
        return resultFinal;

    }


    //Finns en viss sträng, given som inparameter, som svarsalternativ till någon fråga i vår databas?
    public boolean isThisAnAnswerOption(String answerCandidate)
    {
        boolean resultsReturn;
        
        List <String> resultHolder, resultFinal;
    
        List <List> answersList;
    
        AtomicInteger questionsPerListAI;
    
        int listsWithQuestions, questionsPerListInt, allQuestions, laps;
    
    
        resultsReturn = false;
        answersList = new ArrayList <List>();
        questionsPerListAI = new AtomicInteger();
        resultFinal = new ArrayList <String>();
    
    
        questions.stream().forEach(question -> answersList.add(question.getAllAnswers()));
    
    
        listsWithQuestions = answersList.size();
    
    
        questions.stream().forEach(question -> questionsPerListAI.set(question.getAllAnswers().size()));
    
    
        questionsPerListInt = questionsPerListAI.get();
        allQuestions = questionsPerListInt * listsWithQuestions;
        
        
        resultHolder = new ArrayList <String>(allQuestions);
    
    
        for(int i = 0; i < listsWithQuestions; i++)
        {
            int finalI = i;
        
            answersList.stream().distinct().forEach(question -> answersList.get(finalI).toArray(new String[questionsPerListInt]));
        
        }
    
    
        laps = 0;
        for(int i1 = 0; i1 < listsWithQuestions; i1++)
        {
            for(int i2 = 0; i2 < questionsPerListInt; i2++)
            {
                int finalI1 = i1, finalI2 = i2, finalLaps = laps;
            
            
                answersList.stream().distinct().forEach(question -> resultHolder.add(finalLaps , (String) answersList.get(finalI1).get(finalI2)));
            
            
                ++laps;
            
            }
        
        }
    
    
        resultHolder.stream()
        .distinct()
        .sorted()
        .forEach(questions -> resultFinal.add(questions));;
        
        
        for(int i = 0; i < resultFinal.size(); i++)
        {
            if(answerCandidate.equals(resultFinal.get(i)))
            {
                resultsReturn = true;
                break;
            }
        }

        
        return resultsReturn;
        
    }

    //Hur ofta förekommer ett visst svarsalternativ, givet som inparameter, i databasen
    public int getAnswerCandidateFrequncy(String answerCandidate)
    {
        List <String> resultHolder, resultFinal;
    
        List <List> answersList;
    
        AtomicInteger questionsPerListAI;
        
        String tempString;
    
        int resultsReturn , listsWithQuestions, questionsPerListInt, allQuestions, laps;
    
    
        resultsReturn = 0;
        answersList = new ArrayList <List>();
        questionsPerListAI = new AtomicInteger();
    
    
        questions.stream().forEach(question -> answersList.add(question.getAllAnswers()));
    
    
        listsWithQuestions = answersList.size();
    
    
        questions.stream().forEach(question -> questionsPerListAI.set(question.getAllAnswers().size()));
    
    
        questionsPerListInt = questionsPerListAI.get();
        allQuestions = questionsPerListInt * listsWithQuestions;
    
    
        resultHolder = new ArrayList <String>();
        resultFinal = new ArrayList <String>(allQuestions);
        
    
        for(int i = 0; i < listsWithQuestions; i++)
        {
            int finalI = i;
        
            answersList.stream().distinct().forEach(question -> answersList.get(finalI).toArray(new String[questionsPerListInt]));
        
        }
        
    
        laps = 0;
        for(int i1 = 0; i1 < listsWithQuestions; i1++)
        {
            for(int i2 = 0; i2 < questionsPerListInt; i2++)
            {
                int finalI1 = i1, finalI2 = i2, finalLaps = laps;
                
                
                answersList.stream().distinct().forEach(question -> resultHolder.add(finalLaps , (String) answersList.get(finalI1).get(finalI2)));
                
                
                ++laps;
                
            }
        
        }
    
     
        for(int i = 0; i < allQuestions; i++)
        {
            resultFinal.add(i, resultHolder.get(i));
            
        }
        
        
    
        for(int i = 0; i < resultFinal.size(); i++)
        {
            tempString = resultFinal.get(i);
            
            
            if(tempString.equals(answerCandidate))
            {
                ++resultsReturn;
             
            }
            
        }
        
        return resultsReturn;

    }

    //Skapa en Map där kategorierna är nycklar och värdena är en lista
    //av de frågesträngar som tillhör varje kategori
    public Map<Category, List<String>> getQuestionGroupedByCategory()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //Skapa en funktion som hittar det svarsalternativ som har flest bokstäver, i en kategori, given som inparameter
    // OBS: Du måste använda Reduce!
    public String getLongestLettercountAnwerInAGivenCategory(Category c)
    {
        Object[][]
        catagoryAnswers1;
        
        String[]
            catagoryAnswers2;
        
        List<List>
            answersList;
        
        List<String>
            finalStringList1 ,
            finalStringList2;
        
        String
            finalAnswer ,
            tempString;
        
        Object
            tempObject;
        
        AtomicInteger
            questionsPerListAI;
        
        int
            fields ,
            questionsPerListInt ,
            allQuestions ,
            laps ,
            finalLength;
    
    
        finalAnswer = "";
        finalStringList1 = new ArrayList <String>();
        finalStringList2 = new ArrayList <String>();
        questionsPerListAI = new AtomicInteger();
        questions.stream().forEach(question -> questionsPerListAI.set(question.getAllAnswers().size()));
    
    
        answersList = new ArrayList <List>();
        
        
        fields = (int) questions.stream().
            filter(questions -> questions.getCategory().equals(c)).
            count();
    
    
        questionsPerListInt = questionsPerListAI.get();
        allQuestions = questionsPerListInt * fields;
        
        
        catagoryAnswers1 = new Object[fields][questionsPerListInt];
        catagoryAnswers2 = new String[allQuestions];
        
    
        questions.stream()
            .filter(questions -> questions.getCategory().equals(c))
            .forEach(questions -> answersList.add(questions.getAllAnswers()));
    
        
        for(int i1 = 0; i1 < fields; i1++)
        {
            for(int i2 = 0; i2 < questionsPerListInt; i2++)
            {
                int
                    finalI1 = i1,
                    finalI2 = i2;
        
                answersList.stream()
                    .distinct()
                    .forEach(questions -> catagoryAnswers1[finalI1][finalI2] = answersList.get(finalI1).get(finalI2).toString());
    
                
                
            }
            
        }
    
    
        laps = 0;
        for(int i1 = 0; i1 < fields; i1++)
        {
            for(int i2 = 0; i2 < questionsPerListInt; i2++)
            {
                int
                    finalI1 = i1,
                    finalI2 = i2;
                
                tempObject = catagoryAnswers1[finalI1][finalI2];
                tempString = (String) tempObject;
    
    
                catagoryAnswers2[laps] = tempString;
                
                ++laps;
            }
            
        }
    
        
        for(int i = 0; i < allQuestions; i++)
        {
            int finalI = i;
        
            answersList.stream()
            .forEach(questions -> finalStringList1.add(catagoryAnswers2[finalI]));
        }
    
        
        finalStringList1.stream()
            .distinct()
            .sorted()
            .forEach(questions -> finalStringList2.add(questions));
        
    
        finalLength = finalStringList2
            .stream()
            .reduce("", (acc, number) -> acc + number).length();
        
        
        for(int i = 0; i < finalLength; i++)
        {
            int finalI = i;
            
            tempString = finalStringList2.stream()
            .filter(s -> s.length() > finalI)
            .reduce("" , (s1 , s2) -> s1 += s2);
            
            if(tempString.length() > 0)
            {
                finalAnswer = String.valueOf(tempString);
                
            }
            
            else
            {
                break;
                
            }
            
        }

        
        return finalAnswer;
        
    }


    public static void main(String[] args){
        RewriteMe uppg4 = new RewriteMe();
        
    }

}
