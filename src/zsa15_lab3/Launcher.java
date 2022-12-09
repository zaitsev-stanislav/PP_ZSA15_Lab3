package zsa15_lab3;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {

    public static void main(String[] args) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); // Загрузка файла с биновами

            MicrowaveDAO microwaveDAO = (MicrowaveDAO) context.getBean("customerDAO");

            microwaveDAO.deleteAll();
            
            Microwave microwave = new Microwave("ARG", "MS-2021M");
            microwaveDAO.insert(microwave);

            microwaveDAO.insert(new Microwave("Hansa", "AMG F17M2 BH"));
            microwaveDAO.insert(new Microwave("LG", "MS-2042DB"));
            microwaveDAO.insert(new Microwave("Midea", "MM-720CGE-B"));

            System.out.println("Начальная БД:");
            List<Microwave> list = microwaveDAO.selectAll();
            for (Microwave myMicrowave : list) {
                System.out.println(myMicrowave.getBrand()+ " " + myMicrowave.getModel());
            }
            System.out.println();
            
            microwaveDAO.deleteByBrand("nsa");
            microwaveDAO.deleteByModel("2042DB");
            microwaveDAO.delete("Midea", "MM-720CGE-B");

            
            
            System.out.println("Поиск по фрагменту бренда - RG");
            List<Microwave> microwave_list = microwaveDAO.findByBrand("RG");
            if (microwave_list != null) {
                for (Object element : microwave_list) {
                    System.out.println(element);
                }
            } else {
                System.out.println("Нет данных");
            }
            System.out.println();
            
            microwaveDAO.append("Hisense", "H20MOWP1xcb");
            microwaveDAO.append("ARG", "MG-2011M");
            microwaveDAO.append("Avacbv", "AVM-20ХS");
            microwaveDAO.appendOnlyBrand("unknownBrand");
            
            System.out.println("БД перед изменениями:");
            list = microwaveDAO.selectAll();
            for (Microwave myMicrowave : list) {
                System.out.println(myMicrowave.getBrand()+ " " + myMicrowave.getModel());
            }
            System.out.println();

            microwaveDAO.update("H20MOWP1", "H20MOWP1xcb");
            microwaveDAO.updateBrand("Ava", "Avacbv");

            System.out.println("БД после изменений:");
            list = microwaveDAO.selectAll();
            for (Microwave myMicrowave : list) {
                System.out.println(myMicrowave.getBrand() + " " + myMicrowave.getModel());
            }
            System.out.println();
            
            System.out.println("Поиск по фрагменту модели - M-20");
            microwave_list = microwaveDAO.findByModel("M-20");
            if (microwave_list != null) {
                for (Object element : microwave_list) {
                    System.out.println(element);
                }
            } else {
                System.out.println("Нет данных");
            }
            System.out.println();

            System.out.println("Вывод записей с брендом Hisense и моделью H20MOWP1:");

            list = microwaveDAO.select("Hisense", "H20MOWP1");
            for (Microwave myMicrowave : list) {
                System.out.println(myMicrowave.getBrand() + " " + myMicrowave.getModel());
            }
            
            System.out.println("Вывод записей с брендом ARG:");

            list = microwaveDAO.selectByBrand("ARG");
            for (Microwave myMicrowave : list) {
                System.out.println(myMicrowave.getBrand() + " " + myMicrowave.getModel());
            }
            
            System.out.println("Вывод записей с моделью AVM-20ХS:");

            list = microwaveDAO.selectByModel("AVM-20ХS");
            for (Microwave myMicrowave : list) {
                System.out.println(myMicrowave.getBrand() + " " + myMicrowave.getModel());
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!");
        }
    }
    
}
