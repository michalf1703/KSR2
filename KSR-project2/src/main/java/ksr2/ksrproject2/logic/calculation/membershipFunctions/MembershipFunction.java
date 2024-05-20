package ksr2.ksrproject2.logic.calculation.membershipFunctions;

public interface MembershipFunction {

    double getValue(double x);
    double getArea();
    double getLeftBoundary();
    double getRightBoundary();
}

//zastosowanie interfejsu zapewnia nam, że pewne metody muszą być zaimplementowane przez różne typy funkcji przynależności
// możliwość dodawania nowych funkcji przynależności bez konieczności refaktoryzacji
// poprawia czytelność
