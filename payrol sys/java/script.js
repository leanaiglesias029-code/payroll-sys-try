// Constants (same as your Java version)
const GROSS_PAY_15_DAYS = 29000;
const TAX_DEDUCTION = 2000;
const NORMAL_HOURS_PER_DAY = 8;
const DAYS_IN_PERIOD = 15;

const totalNormalHours = DAYS_IN_PERIOD * NORMAL_HOURS_PER_DAY;
const hourlyRate = GROSS_PAY_15_DAYS / totalNormalHours; // ≈ 241.6667
const overtimeRate = hourlyRate * 1.25; // 25% premium

// DOM elements
const nameInput = document.getElementById('employeeName');
const otInput = document.getElementById('overtimeHours');
const addBtn = document.getElementById('addBtn');
const tableBody = document.getElementById('tableBody');
const summarySection = document.getElementById('summary');
const resultDiv = document.getElementById('result');

// Add employee on button click
addBtn.addEventListener('click', addEmployee);

// Also allow Enter key in inputs
[nameInput, otInput].forEach(input => {
  input.addEventListener('keypress', (e) => {
    if (e.key === 'Enter') addEmployee();
  });
});

function addEmployee() {
  const name = nameInput.value.trim();
  const otHours = parseFloat(otInput.value) || 0;

  if (!name) {
    alert('Please enter employee name.');
    return;
  }

  if (otHours < 0) {
    alert('Overtime hours cannot be negative.');
    return;
  }

  // Calculations
  const overtimePay = otHours * overtimeRate;
  const totalGross = GROSS_PAY_15_DAYS + overtimePay;
  const netPay = totalGross - TAX_DEDUCTION;

  // Format numbers with commas and 2 decimals
  const formatPHP = (num) => '₱' + num.toLocaleString('en-PH', { minimumFractionDigits: 2, maximumFractionDigits: 2 });

  // Add row to table
  const row = document.createElement('tr');
  row.innerHTML = `
    <td>${name}</td>
    <td>${otHours.toFixed(2)}</td>
    <td>${formatPHP(overtimePay)}</td>
    <td>${formatPHP(totalGross)}</td>
    <td>${formatPHP(TAX_DEDUCTION)}</td>
    <td>${formatPHP(netPay)}</td>
  `;
  tableBody.appendChild(row);

  // Show summary for the latest entry
  resultDiv.innerHTML = `
    <p><strong>Employee:</strong> ${name}</p>
    <p><strong>Gross (regular):</strong> ${formatPHP(GROSS_PAY_15_DAYS)}</p>
    <p><strong>Overtime Pay:</strong> ${formatPHP(overtimePay)} (${otHours.toFixed(2)} hrs @ ${formatPHP(overtimeRate)}/hr)</p>
    <p><strong>Total Gross:</strong> ${formatPHP(totalGross)}</p>
    <p><strong>Tax Deduction:</strong> ${formatPHP(TAX_DEDUCTION)}</p>
    <p class="net"><strong>Net Pay:</strong> ${formatPHP(netPay)}</p>
  `;
  summarySection.classList.remove('hidden');

  // Clear inputs for next entry
  nameInput.value = '';
  otInput.value = '0';
  nameInput.focus();
}