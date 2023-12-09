package Helpers;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * Генератор сертификатов ( синглтон ).
 * Created by Dima Makieiev on 28.01.2016.
 * Updated by Vladimir V. Klochkov on 25.02.2021.
 */
public class CertificateGenerator {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private static volatile CertificateGenerator instance; // статический экземпляр этого класса

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * Делаем конструктор класса приватным, чтобы не было возможности создать экземпляр класса извне.
     ******************************************************************************************************************/
    private CertificateGenerator() {
    }

    /*******************************************************************************************************************
     * Метод доступа к экземпляру этого класса.
     ******************************************************************************************************************/
    /**
     * Возвращает экземпляр этого класса ( синглтон ).
     *
     * @return экземпляр этого класса
     */
    public static CertificateGenerator getInstance() {
        if (instance == null) {
            synchronized (CertificateGenerator.class) {
                if (instance == null) {
                    instance = new CertificateGenerator();
                }
            }
        }
        return instance;
    }

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    /**
     * Выполняет генерацию сертификата.
     *
     * @param certificateName имя сертификата
     * @param certificateType тип сертификата
     */
    public void generateCertificate(String certificateName, String certificateType) {
        String firstName = certificateName.split(" ")[2];
        String lastName = certificateName.split(" ")[1];
        String utility = "CertificateGenerator\\CertificateGeneratorConsole.exe";
        String cmd = String.format("%s --cn=\"%s\" --type=%s --inn=random --kpp=random --ogrn=random --snils=random " +
                        "-n=1 --gn=1:%s --sn=1:%s --roles=1:FullRts",
                utility, certificateName, certificateType, firstName, lastName);

        System.out.println(StringUtils.repeat("-", 150));
        System.out.println(cmd);

        try {
            //----------------------------------------------------------------------------------------------------------
            Process proc;
            StringBuilder errorMessage;
            int maxGenerateNumber = 4;
            int generateNumber = 0;
            do {
                errorMessage = new StringBuilder();
                generateNumber++;
                System.out.printf("'[ИНФОРМАЦИЯ]': %s попытка генерации сертификата%n", generateNumber);
                proc = Runtime.getRuntime().exec(cmd);
                proc.waitFor();
                TimeUnit.SECONDS.sleep(10);
                //----------------------------------------------------------------------------------------------------------
                BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                System.out.println("Here is the standard output of the command:\n");
                String s = null;
                while ((s = stdInput.readLine()) != null) {
                    System.out.println(s);
                    errorMessage.append(s);
                }
                BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
                System.out.println("Here is the standard error output of the command (if any):\n");
                while ((s = stdError.readLine()) != null) {
                    System.out.println(s);
                    errorMessage.append(s);
                }
            }
            while (errorMessage.length() > 0 && generateNumber < maxGenerateNumber);

            //----------------------------------------------------------------------------------------------------------
        } catch (Exception e) {
            //----------------------------------------------------------------------------------------------------------
            e.printStackTrace();
            System.out.println(StringUtils.repeat("-", 150));
            System.out.println(e.getMessage());
            //----------------------------------------------------------------------------------------------------------
        }
    }


    /**
     * Выполняет генерацию сертификата.
     *
     * @param certificateName имя сертификата
     * @param certificateType тип сертификата
     * @param inn             ИНН сертификата
     * @param kpp             КПП сертификата
     * @param ogrn            ОГРН сертификата
     */
    public void generateCertificate(String certificateName, String certificateType,
                                    String inn, String kpp, String ogrn, String firstName, String lastName) {
        String utility = "CertificateGenerator\\CertificateGeneratorConsole.exe";
        String cmd = String.format("%s --cn=\"%s\" --type=%s --inn=%s --kpp=%s --ogrn=%s --snils=random " +
                        "-n=1 --gn=1:%s --sn=1:%s --roles=1:FullRtsWithoutAdmin ",
                utility, certificateName, certificateType, inn, kpp, ogrn, firstName, lastName);

        System.out.println(StringUtils.repeat("-", 150));
        System.out.println(cmd);

        runCmdProcess(cmd);
    }

    private void runCmdProcess(String cmd) {
        try {
            //----------------------------------------------------------------------------------------------------------
            Process proc;
            StringBuilder errorMessage;
            int maxGenerateNumber = 4;
            int generateNumber = 0;
            do{
                errorMessage = new StringBuilder();
                generateNumber++;
                System.out.printf("'[ИНФОРМАЦИЯ]': %s попытка генерации сертификата%n", generateNumber);
                proc = Runtime.getRuntime().exec(cmd);
                proc.waitFor();
                TimeUnit.SECONDS.sleep(10);
                //------------------------------------------------------------------------------------------------------
                BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                System.out.println("Here is the standard output of the command:\n");
                String s;
                while ((s = stdInput.readLine()) != null){
                    System.out.println(s);
                    errorMessage.append(s);
                }
                BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
                System.out.println("Here is the standard error output of the command (if any):\n");
                while ((s = stdError.readLine()) != null){
                    System.out.println(s);
                    errorMessage.append(s);
                }
            }
            while (errorMessage.length() > 0 && generateNumber < maxGenerateNumber);
                        //----------------------------------------------------------------------------------------------------------
        } catch (Exception e) {
            //----------------------------------------------------------------------------------------------------------
            e.printStackTrace();
            System.out.println(StringUtils.repeat("-", 150));
            System.out.println(e.getMessage());
            //----------------------------------------------------------------------------------------------------------
        }
    }

    /**
     * Удаляет сертификат из реестра Windows.
     *
     * @param certificateName имя сертификата
     */
    public void deleteCertificate(String certificateName) {
        String cmd = String.format("certutil -delstore -user My \"%s\"", certificateName);
        try {
            Process p;
            p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
            TimeUnit.SECONDS.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("[ИНФОРМАЦИЯ]: сертификат: [%s] - УДАЛЕН.%n", certificateName);
    }

    /**
     * Отключает диск с сертификатами для проекта 44-ФЗ. Диск мешает работе генератора сертификатов.
     */
    public void unMountDisk() {
        String cmd = "ImDisk.exe -D -m e:";
        try {
            Process p;
            p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
            TimeUnit.SECONDS.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("[ИНФОРМАЦИЯ]: диск с сертификатами [ОТКЛЮЧЕН].");
    }
}
