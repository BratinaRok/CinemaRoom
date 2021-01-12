package cinema;

import java.util.Scanner;

public class Cinema {

    private static Scanner input = new Scanner(System.in);


    public static void main(String[] args) {

        System.out.println("Enter the number of rows:");
        int numberRows = input.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int numberSeats = input.nextInt();


        String[][] array = new String[numberRows][numberSeats];

        seatOrganize(numberSeats, numberRows, array);
        menuOptions();
        int countTickets = 0;
        int reservedRow = 0;
        int reservedSeat = 0;
        boolean test = false;
        boolean exit = false;

        while (!exit) {
            int option = input.nextInt();
            switch (option) {

                case 0:
                    exit = true;
                    break;

                case 1:
                    showSeats(numberSeats, numberRows, array);
                    menuOptions();
                    break;

                case 2:
                    System.out.println();
                    while (!test) {
                        System.out.println("Enter a row number:");
                        reservedRow = input.nextInt();

                        System.out.println("Enter a seat number in that row:");
                        reservedSeat = input.nextInt();

                        if ((reservedRow > array.length) || (reservedSeat > array.length)) {
                            System.out.println("Wrong input!");
                        } else if (array[reservedRow - 1][reservedSeat - 1].equals("B")) {
                            System.out.println("That ticket has already been purchased!");
                        } else {
                            test = true;
                        }
                    }
                    countTickets++;
                    buyTicket(numberRows, numberSeats, reservedRow, reservedSeat, array);
                    test = false;
                    menuOptions();
                    break;

                case 3:
                    statistics(numberRows, numberSeats, reservedRow, countTickets, array);
                    menuOptions();
                    break;
            }
        }

    }


    public static void menuOptions() {

        System.out.println("1. Show the seats \n" + "2. Buy a ticket \n" + "3. Statistics \n" + "0. Exit");
    }


    public static void seatOrganize(int numberSeats, int numberRows, String[][] cinemaRoomArray) {
        String[][] cinemaRoom = cinemaRoomArray;
        String S = "S";
        for (int i = 0; i < numberSeats; i++) {

        }

        for (int i = 0; i < numberRows; i++) {
            for (int j = 0; j < numberSeats; j++) {
                if (cinemaRoom[i][j] == null) {
                    cinemaRoom[i][j] = "S";
                }

            }

        }
    }


    public static void showSeats(int numberSeats, int numberRows, String[][] cinemaRoomArray) {

        String[][] cinemaRoom = cinemaRoomArray;

        System.out.println();
        System.out.println("Cinema:");
        String S = "S";

        System.out.print(" ");
        for (int i = 0; i < numberSeats; i++) {
            System.out.print(" " + (i + 1));
        }
        System.out.println();

        for (int i = 0; i < numberRows; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < numberSeats; j++) {
                if (cinemaRoom[i][j] == null) {
                    cinemaRoom[i][j] = "S";
                }
                System.out.print(" " + cinemaRoom[i][j]);
            }
            System.out.println();
        }
        System.out.println();


    }


    public static void buyTicket(int numberRows, int numberSeats, int reservedRow, int reservedSeat, String[][] array) {

        System.out.println();
        int frontHalfOfRows = 0;
        int ticketPrice = 0;
        int totalNumberOfSeats = numberRows * numberSeats;

        if (totalNumberOfSeats <= 60) {
            ticketPrice = 10;
        } else if (numberRows % 2 == 0) {
            frontHalfOfRows = numberRows / 2;
            if (frontHalfOfRows >= reservedRow) {
                ticketPrice = 10;
            } else {
                ticketPrice = 8;
            }
        } else {
            double numberRowsDecimal = (double) numberRows / 2;
            frontHalfOfRows = (int) (numberRowsDecimal);
            if (frontHalfOfRows >= reservedRow) {
                ticketPrice = 10;

            } else {
                ticketPrice = 8;
            }
        }


        System.out.println("Ticket price: " + "$" + ticketPrice);
        System.out.println();

        reservedSeats(numberRows, numberSeats, reservedRow, reservedSeat, array);

    }


    public static void reservedSeats(int numberRows, int numberSeats, int reservedRow, int reservedSeat, String[][] array) {

        String B = "B";

        for (int i = 0; i < numberRows; i++) {

            for (int j = 0; j < numberSeats; j++) {
                array[(reservedRow - 1)][(reservedSeat - 1)] = B;
            }
        }
    }


    public static void statistics(int numberRows, int numberSeats, int reservedRow, int countTickets, String[][] reservedSeats) {

        double percentage = 0.00d;
        System.out.println();
        System.out.println("Number of purchased tickets: " + countTickets);

        int income = 0;

        int totalTicketPrice = 0;
        int frontHalfOfRows = 0;
        int backHalfOfRows = 0;
        int ticketPrice = 0;
        int totalNumberOfSeats = numberRows * numberSeats;


        percentage = (double) (countTickets * 100) / totalNumberOfSeats;


        if (totalNumberOfSeats <= 60) {
            ticketPrice = 10;
            totalTicketPrice = totalNumberOfSeats * ticketPrice;
            income = countTickets * ticketPrice;

        } else if (numberRows % 2 == 0) {
            frontHalfOfRows = (numberRows / 2) * 10;
            backHalfOfRows = (numberRows / 2) * 8;

            totalTicketPrice = frontHalfOfRows + backHalfOfRows;

            if ((numberRows / 2) < reservedRow) {
                income = countTickets * 10;
            } else {
                income = countTickets * 8;
            }


        } else {
            double numberRowsDecimal = (double) numberRows / 2;
            frontHalfOfRows = ((int) (numberRows - (numberRowsDecimal - 0.5)) * 8);

            backHalfOfRows = ((int) (numberRows - (numberRowsDecimal + 0.5)) * 10);
            totalTicketPrice = (frontHalfOfRows + backHalfOfRows) * numberRows;


            for (int i = 0; i < numberRows; i++) {
                for (int j = 0; j < numberSeats; j++) {
                    if (reservedSeats[i][j].equals("B") && (i + 1) < (numberRows - (numberRowsDecimal - 0.5))) {
                        income += 10;
                        System.out.println("1i= " + i);
                    } else if (reservedSeats[i][j].equals("B") && (i + 1) >= (numberRows - (numberRowsDecimal - 0.5))) {
                        System.out.println("2i= " + i);
                        income += 8;
                    }
                }
            }


        }

        System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");
        System.out.println("Current income: $" + income);
        System.out.println("Total income: " + "$" + totalTicketPrice);
        System.out.println();
    }


}

