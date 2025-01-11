@echo

if [ -z "$1" ]; then
  echo "Tidak ada file input. Menjalankan aplikasi secara manual."
  mvn -q exec:java -Dexec.mainClass=com.simulator.credit.Main
else
  echo "Menjalankan aplikasi dengan file input: $1"
  mvn -q exec:java -Dexec.mainClass=com.simulator.credit.Main -Dexec.args="$1"
fi

pause