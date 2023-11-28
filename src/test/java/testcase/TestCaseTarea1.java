package testcase;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;


import java.nio.charset.StandardCharsets;

import static org.openqa.selenium.Keys.TAB;

public class TestCaseTarea1 {
    WebDriver driver;

    @BeforeEach
    public void preCondiciones(){
        //String ruta del driver
        String rutaDriver = "D:\\Java\\CursoSelenium\\EjerciciosSelenium1\\src\\test\\resources\\driver\\chromedriver.exe";

        //Enlazar el driver como property de windows para el manejo
        //Del navegador
        System.setProperty("webdriver.chrome.driver",rutaDriver);

        //Instanciar un objeto de tipo driver de chrome
        driver = new ChromeDriver();
        //driver.get("https://sitio.consorcio.cl/");
         driver.get("https://sitio.consorcio.cl/banca-personas/cuenta-vista/cuenta-mas-digital/entry");
        //driver.get("https://www.emol.com/");



        //maximizar el browser
        driver.manage().window().maximize();
    }
    @AfterEach
    public void posCondiciones(){
        //pos condicion
      //  driver.quit();
    }
 /*
    @Test
    void ingresoClienteExistente() throws InterruptedException {

        WebElement btnPortate = driver.findElement(By.id("hi_header_login_a"));
        WebElement btnHazteCliente = driver.findElement(By.id("header_hazte_cliente_banco"));

        btnPortate.click();
        if (btnHazteCliente.isDisplayed())
            btnHazteCliente.click();



        WebElement btnContinuar = driver.findElement(By.id("step1_btn"));
        WebElement txtIngresoRut = driver.findElement(By.name("rut"));
        txtIngresoRut.sendKeys("14270952-K");
        driver.findElement(By.name("email")).sendKeys("t@mail.cl");
        driver.findElement(By.name("celular")).sendKeys("999999999");
        driver.findElement(By.id("nacionalidadChileno")).click();
        driver.findElement(By.id("otraNacionalidadNo" )).click();
        Thread.sleep(2000); //ESPERA 3 SEGUNDOS


        String resultadoActual = corregirFormatoTexto(driver.findElement(By.className("title")).getText());
        //<div data-v-992f5938="" class="title">Ya tienes una cuenta vista vigente</div>

        String resultadoEsperado = "Ya tienes una cuenta vista vigente";
        Thread.sleep(2000); //ESPERA xx SEGUNDOS
        if(resultadoEsperado == resultadoActual){
            // System.out.println("existe boton");
          //  btnContinuar.click();

         }

            // si resultado esperado es igual a resultado obtenido pasa la prueba
            Assertions.assertEquals(resultadoEsperado,resultadoActual);

    }
*/



    @Test
    void ingresoClienteExistenteFuncionario() throws InterruptedException {

        WebElement btnPortate = driver.findElement(By.id("hi_header_login_a"));
        WebElement btnHazteCliente = driver.findElement(By.id("header_hazte_cliente_banco"));

        btnPortate.click();
        if (btnHazteCliente.isDisplayed())
            btnHazteCliente.click();


        WebElement txtIngresoRut = driver.findElement(By.name("rut"));
        WebElement txtCelular = driver.findElement(By.name("celular"));

        txtIngresoRut.sendKeys("12646609-9");
    //    txtIngresoRut.sendKeys(TAB);
        driver.findElement(By.id("nacionalidadChileno")).click();
        driver.findElement(By.id("otraNacionalidadNo" )).click();
    // realiza scroll
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,400)");


        driver.findElement(By.name("email")).sendKeys("t@mail.cl");
       // driver.findElement(By.name("celular")).sendKeys("999999999");
        txtCelular.sendKeys("999999999");
       //envia una tabulador para avanzar
        txtCelular.sendKeys(TAB);

        WebElement btnContinuar = driver.findElement(By.id("step1_btn"));
        // presiona botón continuar, ahora le cambiaron de nombre
        btnContinuar.click();

       Thread.sleep(4000); //delay en milisegundos para esperar mensaje emergente

        //WebElement btnIrConsorcio = driver.findElement(By.xpath("//cns-button[@label='Ir a Consorcio.cl']"));
        // busca titulo para realizar comparacion
        String resultadoActual;
        resultadoActual = corregirFormatoTexto(driver.findElement(By.className("title")).getText());
        String resultadoEsperado = "¡Eres funcionario de Consorcio!";

//  establece resultado del test.
        Assertions.assertEquals(resultadoEsperado,resultadoActual);

    }

 /*
    @Test
    void validaActivacionBoton() throws InterruptedException {
// ingreso a pagina principal de sitio consorcio
        WebElement btnPortate = driver.findElement(By.id("hi_header_login_a"));
        WebElement btnHazteCliente = driver.findElement(By.id("header_hazte_cliente_banco"));

                btnPortate.click();
        if (btnHazteCliente.isDisplayed())
            btnHazteCliente.click();
// en página siguiente, valida que botón está deshabilitado.
        boolean resultadoActual;
        boolean resultadoEsperado = true;

        WebElement btnContinuar = driver.findElement(By.id("step1_btn"));
        WebElement txtIngresoRut = driver.findElement(By.name("rut"));
         resultadoActual =btnContinuar.isEnabled();


        txtIngresoRut.sendKeys("12646609-9");
         driver.findElement(By.name("email")).sendKeys("t@mail.cl");
        driver.findElement(By.name("celular")).sendKeys("999999999");
        driver.findElement(By.id("nacionalidadChileno")).click();
        driver.findElement(By.id("otraNacionalidadNo" )).click();
        Thread.sleep(2000); //ESPERA x SEGUNDOS
        resultadoActual =btnContinuar.isEnabled();

        Assertions.assertEquals(resultadoEsperado,resultadoActual);

    }




    @Test
    void validaMensajesTextBoxVacios() throws InterruptedException {
// ingreso a pagina principal de sitio consorcio
        WebElement btnPortate = driver.findElement(By.id("hi_header_login_a"));
        WebElement btnHazteCliente = driver.findElement(By.id("header_hazte_cliente_banco"));

        btnPortate.click();
        if (btnHazteCliente.isDisplayed())
            btnHazteCliente.click();
// en página siguiente, valida que botón está deshabilitado.
        boolean resultadoActual = false;
        boolean resultadoEsperado = false;

        WebElement txtIngresoRut = driver.findElement(By.name("rut"));
        WebElement txtIngresoEmail = driver.findElement(By.name("email"));
        WebElement txtIngresoCelular = driver.findElement(By.name("celular"));


        txtIngresoRut.sendKeys("12646609-9");
        driver.findElement(By.id("nacionalidadChileno")).click();
        driver.findElement(By.id("otraNacionalidadNo" )).click();
        txtIngresoEmail.sendKeys(TAB);
        txtIngresoCelular.sendKeys(TAB);




        Assertions.assertEquals(resultadoEsperado,resultadoActual);
//webElement.sendKeys(Keys.TAB);
    }

    void ingresoclienteSinCuenta() throws InterruptedException {

    }
*/






    public static String corregirFormatoTexto(String textoIncorrecto){
        byte[] bytes = textoIncorrecto.getBytes(StandardCharsets.UTF_8);
        String textoCorrejido = new String(bytes, StandardCharsets.ISO_8859_1);

        return textoCorrejido;
    }
}
