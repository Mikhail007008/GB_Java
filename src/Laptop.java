public class Laptop {
    String name;
    int ram;
    int hardDrive;
    String os;
    String color;

    public Laptop(String name, int ram, int hardDrive, String os, String color){
        this.name = name;
        this.ram = ram;
        this.hardDrive = hardDrive;
        this.os = os;
        this.color = color;
    }

    public String toString() {
        return "Наименование: " + name + ", ОЗУ: " + ram + ", Жесткий диск: " + hardDrive + ", ОС: " + os + ", Цвет: " + color;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Laptop laptop)){
            return false;
        }
        return ram == laptop.ram && hardDrive == laptop.hardDrive && os.equals(laptop.os) && color.equals(laptop.color);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + 7*ram + 13*hardDrive + 15*os.hashCode() + 11*color.hashCode();
    }
}
