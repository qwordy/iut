srcdir=/home/yfy/iut/src/C++/test
make=make
run=./main

cd $srcdir
$make clean
$make
$run

# walk through dir, gcov
walk() {
  for file in `ls $1`
  do
    local path=$1/$file
    if [ -f $path ] && [ ${file##*.} = gcda ]
    then
      echo gcov $path
      gcov -b $1/${file%.*} > /dev/null
    elif [ -d $path ]
    then
      echo enter $path
      walk $path
      echo leave $path
    fi
  done
  rm -f $1/*.gcno $1/*.gcda
}

walk $srcdir

# analyse *.gcov
for file in *
do
  path=$srcdir/$file
  if [ -f $path ] && [ ${file##*.} = gcov ]
  then
    echo analyse $path
  fi
done
rm -f *.gcov


