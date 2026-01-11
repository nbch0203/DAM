//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class Intento {
//
//    public static boolean verificarCalculos(String filePath) throws Exception {
//        // Abrir el documento Word
//        FileInputStream fis = new FileInputStream(new File(filePath));
//        XWPFDocument document = new XWPFDocument(fis);
//
//        // Patrón para encontrar cálculos (ejemplo: "2+2=4")
//        Pattern pattern = Pattern.compile("(\\d+\\s*[+\\-*/]\\s*\\d+)\\s*=\\s*(\\d+)");
//        boolean correcto = true;
//        double baseImponible = 0;
//
//        for (XWPFParagraph paragraph : document.getParagraphs()) {
//            String text = paragraph.getText();
//
//            // Buscar base imponible
//            if (text.contains("BASE IMPONIBLE")) {
//                baseImponible = extraerNumeroDeTexto(text);
//            }
//
//            // Verificar cálculos simples
//            Matcher matcher = pattern.matcher(text);
//            while (matcher.find()) {
//                String operacion = matcher.group(1).replaceAll("\\s", ""); // Ejemplo: "2+2"
//                int resultadoEsperado = Integer.parseInt(matcher.group(2)); // Ejemplo: "4"
//
//                int resultadoCalculado = evaluarOperacion(operacion);
//                if (resultadoCalculado != resultadoEsperado) {
//                    System.out.println("Error: " + operacion + " = " + resultadoEsperado + " es incorrecto.");
//                    correcto = false;
//                }
//            }
//        }
//
//        // Realizar cálculos de IVA, IRPF y total si la base imponible fue encontrada
//        if (baseImponible > 0) {
//            System.out.println("Base Imponible detectada: " + baseImponible + "€");
//            double iva = calcularPorcentaje(baseImponible, 21);
//            double irpf = calcularPorcentaje(baseImponible, 1);
//            double total = baseImponible + iva - irpf;
//
//            System.out.println("IVA (21%): " + iva + "€");
//            System.out.println("IRPF (1%): " + irpf + "€");
//            System.out.println("Total calculado: " + total + "€");
//        } else {
//            System.out.println("No se detectó base imponible en el documento.");
//            correcto = false;
//        }
//
////        document.close();
//        fis.close();
//        return correcto;
//    }
//
//    public static int evaluarOperacion(String operacion) {
//        // Usar un motor de scripts para evaluar la operación matemática
//        try {
//            javax.script.ScriptEngineManager manager = new javax.script.ScriptEngineManager();
//            javax.script.ScriptEngine engine = manager.getEngineByName("JavaScript");
//            return ((Number) engine.eval(operacion)).intValue();
//        } catch (Exception e) {
//            throw new RuntimeException("Error al evaluar la operación: " + operacion, e);
//        }
//    }
//
//    public static double calcularPorcentaje(double base, double porcentaje) {
//        return base * (porcentaje / 100);
//    }
//
//    public static double extraerNumeroDeTexto(String texto) {
//        try {
//            // Extraer el número usando una expresión regular
//            String numero = texto.replaceAll("[^0-9.,]", "").replace(",", ".");
//            return Double.parseDouble(numero);
//        } catch (NumberFormatException e) {
//            return 0;
//        }
//    }
//
//    public static void procesarDocumentos(String inputFolder, String outputFolder) throws Exception {
//        File folder = new File(inputFolder);
//        if (!folder.exists()) {
//            System.out.println("La carpeta de entrada no existe.");
//            return;
//        }
//
//        File outputDir = new File(outputFolder);
//        if (!outputDir.exists())
//            outputDir.mkdirs();
//
//        for (File file : folder.listFiles()) {
//            if (file.getName().endsWith(".docx")) {
//                String inputPath = file.getAbsolutePath();
//                String outputPath = new File(outputDir, file.getName().replace(".docx", ".pdf")).getAbsolutePath();
//
//                System.out.println("Procesando " + file.getName());
//                if (verificarCalculos(inputPath)) {
//                    System.out.println("Los cálculos son correctos. Convirtiendo a PDF...");
//                    convertirWordAPdf(inputPath, outputPath);
//                } else {
//                    System.out.println("Cálculos incorrectos. No se generará PDF para " + file.getName());
//                }
//            }
//        }
//    }
//
//    public static void convertirWordAPdf(String inputPath, String outputPath) throws Exception {
//        // Usar Aspose para convertir Word a PDF (requiere licencia)
//        com.aspose.words.Document doc = new com.aspose.words.Document(inputPath);
//        doc.save(outputPath, com.aspose.words.SaveFormat.PDF);
//    }
//
//    public static void main(String[] args) throws Exception {
//        String inputFolder = "C:/ruta/a/carpeta/entrada";
//        String outputFolder = "C:/ruta/a/carpeta/salida";
//
//        procesarDocumentos(inputFolder, outputFolder);
//    }
//}
