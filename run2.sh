# execute using:
# ./run2.sh mileage-test-2.0 com.evancharlton.mileage 1 1000

echo "Preparing Appium Environment for $1 and $2"
echo "Executing for original apk"
echo ""
java -jar mileage-test-2.0.jar com.evancharlton.mileage.apk -o ./results/com.evancharlton.mileage
echo "Preparing VRT"
echo ""
cd backstop
cp backstop.json.template backstop.json
REF=$(echo "$(cd "$(dirname "../results/com.evancharlton.mileage/screenshots/before-create-vehicle.png")" && pwd -P)/$(basename "../results/com.evancharlton.mileage/screenshots/before-create-vehicle.png")")
sed -i -e "s+<<before-create-vehicle-reference>>+$REF+g" "backstop.json"
REF=$(echo "$(cd "$(dirname "../results/com.evancharlton.mileage/screenshots/after-create-vehicle.png")" && pwd -P)/$(basename "../results/com.evancharlton.mileage/screenshots/after-create-vehicle.png")")
sed -i -e "s+<<after-create-vehicle-reference>>+$REF+g" "backstop.json"
REF=$(echo "$(cd "$(dirname "../results/com.evancharlton.mileage/screenshots/before-register-fillup.png")" && pwd -P)/$(basename "../results/com.evancharlton.mileage/screenshots/before-register-fillup.png")")
sed -i -e "s+<<before-register-fillup-reference>>+$REF+g" "backstop.json"
REF=$(echo "$(cd "$(dirname "../results/com.evancharlton.mileage/screenshots/after-register-fillup.png")" && pwd -P)/$(basename "../results/com.evancharlton.mileage/screenshots/after-register-fillup.png")")
sed -i -e "s+<<after-register-fillup-reference>>+$REF+g" "backstop.json"
REF=$(echo "$(cd "$(dirname "../results/com.evancharlton.mileage/screenshots/history.png")" && pwd -P)/$(basename "../results/com.evancharlton.mileage/screenshots/history.png")")
sed -i -e "s+<<history-reference>>+$REF+g" "backstop.json"
cp backstop.json backstop.json.mutant.template
echo "Taking VRT Reference Captures"
echo ""
./node_modules/.bin/backstop reference

for (( c=$3; c<=$4; c++ ))
do
  cd ..
  echo "Executing for Mutant $c"
  echo ""
  java -jar $1.jar parcial2/$2-mutant$c/com.evancharlton.mileage_3110-aligned-debugSigned.apk -o ./results/$2-mutant$c
  echo "Taking VRT Test Captures for Mutant $c"
  echo ""
  cd backstop
  cp backstop.json.mutant.template backstop.json
  TEST=$(echo "$(cd "$(dirname "../results/$2-mutant$c/screenshots/before-create-vehicle.png")" && pwd -P)/$(basename "../results/$2-mutant$c/screenshots/before-create-vehicle.png")")
  sed -i -e "s+<<before-create-vehicle-test>>+$TEST+g" "backstop.json"
  TEST=$(echo "$(cd "$(dirname "../results/$2-mutant$c/screenshots/after-create-vehicle.png")" && pwd -P)/$(basename "../results/$2-mutant$c/screenshots/after-create-vehicle.png")")
  sed -i -e "s+<<after-create-vehicle-test>>+$TEST+g" "backstop.json"
  TEST=$(echo "$(cd "$(dirname "../results/$2-mutant$c/screenshots/before-register-fillup.png")" && pwd -P)/$(basename "../results/$2-mutant$c/screenshots/before-register-fillup.png")")
  sed -i -e "s+<<before-register-fillup-test>>+$TEST+g" "backstop.json"
  TEST=$(echo "$(cd "$(dirname "../results/$2-mutant$c/screenshots/after-register-fillup.png")" && pwd -P)/$(basename "../results/$2-mutant$c/screenshots/after-register-fillup.png")")
  sed -i -e "s+<<after-register-fillup-test>>+$TEST+g" "backstop.json"
  TEST=$(echo "$(cd "$(dirname "../results/$2-mutant$c/screenshots/history.png")" && pwd -P)/$(basename "../results/$2-mutant$c/screenshots/history.png")")
  sed -i -e "s+<<history-test>>+$TEST+g" "backstop.json"
  ./node_modules/.bin/backstop test
  if [ ! -d "../results/$2-mutant$c/vrt_report/" ]
  then
      mkdir ../results/$2-mutant$c/vrt_report/
  fi
  mv backstop_data/html_report ../results/$2-mutant$c/vrt_report/html_report
  cp -r backstop_data/bitmaps_reference ../results/$2-mutant$c/vrt_report/bitmaps_reference
  mv backstop_data/bitmaps_test ../results/$2-mutant$c/vrt_report/bitmaps_test
  mv backstop_data/ci_report ../results/$2-mutant$c/vrt_report/ci_report
done
#java -jar $1.jar $2.apk -o ./results/$2
