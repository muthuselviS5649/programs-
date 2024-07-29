import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(int temperature);
}

class WeatherStation {
    private List<Observer> observers = new ArrayList<>();
    private int temperature;

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        notifyObservers();
    }
}

class TemperatureDisplay implements Observer {
    @Override
    public void update(int temperature) {
        System.out.println("Temperature updated to " + temperature + " degrees.");
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();
        TemperatureDisplay display1 = new TemperatureDisplay();
        TemperatureDisplay display2 = new TemperatureDisplay();

        weatherStation.addObserver(display1);
        weatherStation.addObserver(display2);

        weatherStation.setTemperature(25);
        weatherStation.setTemperature(30);
    }
}
