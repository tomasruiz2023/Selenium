package ejercicios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestPrueba1 {

    public static void main(String[] args) throws InterruptedException {

        //RUTA DRIVER
        String rutaDriver = "D:\\Java\\CursoSelenium\\EjerciciosSelenium1\\src\\test\\resources\\driver\\chromedriver.exe";

        //Enlazar el driver como property de windows para el manejo

        System.setProperty("webdriver.chrome.driver",rutaDriver);

        //Instanciar un objeto de tipo driver de chrome
        WebDriver driver = new ChromeDriver();

        //web del banco
        driver.get("https://sitio.consorcio.cl/");

        //Maximizar ventana
        driver.manage().window().maximize();
        //By locatorBtnPortate = By.xpath("//button[@id='hi_header_login_a']");
       // By locatorIdhaztecliente = By.id("header_hazte_cliente_banco");//id="header_hazte_cliente_banco"
       // By locatorIdPortate = By.id("hi_header_login_a");

       // WebElement btnPortate = driver.findElement(locatorIdPortate);
       // WebElement btnHazteCliente = driver.findElement(locatorIdhaztecliente);


        WebElement btnPortate = driver.findElement(By.id("hi_header_login_a"));
        WebElement btnHazteCliente = driver.findElement(By.id("header_hazte_cliente_banco"));

        btnPortate.click();
        if (btnHazteCliente.isDisplayed())
            btnHazteCliente.click();

        WebElement txtIngresoRut = driver.findElement(By.name("rut"));
        txtIngresoRut.sendKeys("12646609-9");

        driver.findElement(By.id("nacionalidadChileno")).click();
        driver.findElement(By.id("otraNacionalidadNo" )).click();
        driver.findElement(By.name("email")).sendKeys("t@mail.cl");
        driver.findElement(By.name("celular")).sendKeys("999999999");

        driver.findElement(By.xpath("//cns-button[@label='Continuar']")).click();


        //sleep(10000); //ESPERA 10 SEGUNDOS
        //driver.manage().wait(4000);
        //driver.close();
        //driver.quit();

    }

}
