import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Parser {
    static List<Country> countries = new ArrayList<>();

    public List<Country> sortByName(){
        List<Country> sortedByName = new ArrayList<>(countries);
        // Sort countries alphabetically (least)
        for (int i = 0; i < sortedByName.size(); i++){
            for (int j = 0; j < sortedByName.size(); j++) {
                if (sortedByName.get(i).getName().compareToIgnoreCase(sortedByName.get(j).getName()) < 0){
                    Collections.swap(sortedByName, i, j);
                }
            }
        }
        return  sortedByName;
    }

    public List<Country> sortByPopulation(){
        List<Country> sortedByPopulation = new ArrayList<>(countries);
        // Sort countries by population (most)
        for (int i = 0; i < sortedByPopulation.size(); i++){
            for (int j = 0; j < sortedByPopulation.size(); j++) {
                if (sortedByPopulation.get(i).getPopulation() > sortedByPopulation.get(j).getPopulation()){
                    Collections.swap(sortedByPopulation, i, j);
                }
            }
        }
        return sortedByPopulation;
    }

    public List<Country> sortByArea(){
        List<Country> sortedByArea = new ArrayList<>(countries);
        for (int i = 0; i < sortedByArea.size(); i++){
            for (int j = 0; j < sortedByArea.size(); j++) {
                if (sortedByArea.get(i).getArea() > sortedByArea.get(j).getArea()){
                    Collections.swap(sortedByArea, i, j);
                }
            }
        }
        return sortedByArea;
    }

    public void setUp() throws IOException {
        //getting the html file
        File htmlFile= new File("src\\Resources\\country-list.html");
        String htmlString = null;
        Scanner scanner = new Scanner(htmlFile);
        //reading html file lines and storing it in htmlString
        while (scanner.hasNextLine()){
            htmlString = htmlString + scanner.nextLine();
        }
        //closing scanner
        scanner.close();
        //making a document file to parse html
        Document document = Jsoup.parse(htmlString);
        //every country lies in a .col-md-4.country class, we select all of them
        Elements countries = document.select(".col-md-4.country");
        for (Element country : countries) {
            //getting country name
            String countryName = country.selectFirst(".country-name").text().trim();
            //getting country capital
            String capital = country.selectFirst(".country-capital").text().trim();
            //getting population
            int population = Integer.parseInt(country.selectFirst(".country-population").text().trim());
            //getting area
            double area = Double.parseDouble(country.selectFirst(".country-area").text().trim());
            //making a country object to store data
            Country countryObject = new Country(countryName, capital, population, area);
            this.countries.add(countryObject);
        }
    }

    public static void main(String[] args) {
    }
}
