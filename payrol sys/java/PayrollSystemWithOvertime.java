import java.util.Scanner;

/**
 * Payroll System with Overtime Calculation (Philippine Pesos)
 * - Fixed 15-day gross: ₱29,000
 * - Flat tax deduction: ₱2,000
 * - Overtime: 25% premium on hourly rate for hours beyond normal
 * - Hourly rate derived from gross / (15 days * 8 hours) ≈ ₱241.67/hour
 * - Handles multiple employees; type 'exit' to quit
 * Great for 4th-year CS project – easy to extend!
 */
public class PayrollSystemWithOvertime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final double GROSS_PAY_15_DAYS = 29000.00;     // Fixed gross for 15 days
        final double TAX_DEDUCTION      = 2000.00;     // Flat tax per 15 days
        final double NORMAL_HOURS_PER_DAY = 8.0;
        final double DAYS_IN_PERIOD     = 15.0;

        // Calculate hourly rate from gross pay
        double totalNormalHours = DAYS_IN_PERIOD * NORMAL_HOURS_PER_DAY;
        double hourlyRate = GROSS_PAY_15_DAYS / totalNormalHours;  // ≈ 241.6667

        // Overtime premium rate (25% extra)
        double overtimeRate = hourlyRate * 1.25;

        System.out.println("=== Simple Payroll System with Overtime (Philippines 2026 Rules) ===");
        System.out.println("Gross Pay (15 days): ₱" + String.format("%.2f", GROSS_PAY_15_DAYS));
        System.out.printf("Hourly Rate: ₱%.2f | Overtime Rate: ₱%.2f/hour (25%% premium)\n", hourlyRate, overtimeRate);
        System.out.println("Flat Tax Deduction: ₱" + String.format("%.2f", TAX_DEDUCTION));
        System.out.println("Enter employee details or type 'exit' to quit.\n");

        while (true) {
            System.out.print("Enter employee name (or 'exit' to quit): ");
            String employeeName = scanner.nextLine().trim();

            if (employeeName.equalsIgnoreCase("exit")) {
                System.out.println("\nExiting payroll system. Salamat!");
                break;
            }

            // Get overtime hours (for the entire 15-day period)
            double overtimeHours = 0.0;
            boolean validInput = false;
            while (!validInput) {
                System.out.print("Enter total overtime hours worked in the 15-day period: ");
                try {
                    overtimeHours = Double.parseDouble(scanner.nextLine().trim());
                    if (overtimeHours >= 0) {
                        validInput = true;
                    } else {
                        System.out.println("Overtime hours cannot be negative. Try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number. Please enter a valid number (e.g., 10.5).");
                }
            }

            // Calculations
            double overtimePay = overtimeHours * overtimeRate;
            double totalGross = GROSS_PAY_15_DAYS + overtimePay;
            double netPay = totalGross - TAX_DEDUCTION;

            // Display results
            System.out.println("\n--- Payroll Summary for " + employeeName + " ---");
            System.out.printf("Gross Pay (regular 15 days): ₱%,.2f\n", GROSS_PAY_15_DAYS);
            System.out.printf("Overtime Hours: %.2f | Overtime Pay: ₱%,.2f\n", overtimeHours, overtimePay);
            System.out.printf("Total Gross Pay: ₱%,.2f\n", totalGross);
            System.out.printf("Tax Deduction: ₱%,.2f\n", TAX_DEDUCTION);
            System.out.printf("Net Pay (Take-home): ₱%,.2f\n", netPay);
            System.out.println("--------------------------------------------\n");
        }

        scanner.close();
    }
}
