# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.0

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list

# Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/bin/cmake

# The command to remove a file.
RM = /usr/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/yfy/iut/benchmarks/avro-cpp-1.8.0

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/yfy/iut/benchmarks/avro-cpp-1.8.0

# Include any dependencies generated for this target.
include CMakeFiles/LargeSchemaTests.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/LargeSchemaTests.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/LargeSchemaTests.dir/flags.make

CMakeFiles/LargeSchemaTests.dir/test/LargeSchemaTests.cc.o: CMakeFiles/LargeSchemaTests.dir/flags.make
CMakeFiles/LargeSchemaTests.dir/test/LargeSchemaTests.cc.o: test/LargeSchemaTests.cc
	$(CMAKE_COMMAND) -E cmake_progress_report /home/yfy/iut/benchmarks/avro-cpp-1.8.0/CMakeFiles $(CMAKE_PROGRESS_1)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object CMakeFiles/LargeSchemaTests.dir/test/LargeSchemaTests.cc.o"
	/usr/bin/c++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/LargeSchemaTests.dir/test/LargeSchemaTests.cc.o -c /home/yfy/iut/benchmarks/avro-cpp-1.8.0/test/LargeSchemaTests.cc

CMakeFiles/LargeSchemaTests.dir/test/LargeSchemaTests.cc.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/LargeSchemaTests.dir/test/LargeSchemaTests.cc.i"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -E /home/yfy/iut/benchmarks/avro-cpp-1.8.0/test/LargeSchemaTests.cc > CMakeFiles/LargeSchemaTests.dir/test/LargeSchemaTests.cc.i

CMakeFiles/LargeSchemaTests.dir/test/LargeSchemaTests.cc.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/LargeSchemaTests.dir/test/LargeSchemaTests.cc.s"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -S /home/yfy/iut/benchmarks/avro-cpp-1.8.0/test/LargeSchemaTests.cc -o CMakeFiles/LargeSchemaTests.dir/test/LargeSchemaTests.cc.s

CMakeFiles/LargeSchemaTests.dir/test/LargeSchemaTests.cc.o.requires:
.PHONY : CMakeFiles/LargeSchemaTests.dir/test/LargeSchemaTests.cc.o.requires

CMakeFiles/LargeSchemaTests.dir/test/LargeSchemaTests.cc.o.provides: CMakeFiles/LargeSchemaTests.dir/test/LargeSchemaTests.cc.o.requires
	$(MAKE) -f CMakeFiles/LargeSchemaTests.dir/build.make CMakeFiles/LargeSchemaTests.dir/test/LargeSchemaTests.cc.o.provides.build
.PHONY : CMakeFiles/LargeSchemaTests.dir/test/LargeSchemaTests.cc.o.provides

CMakeFiles/LargeSchemaTests.dir/test/LargeSchemaTests.cc.o.provides.build: CMakeFiles/LargeSchemaTests.dir/test/LargeSchemaTests.cc.o

# Object files for target LargeSchemaTests
LargeSchemaTests_OBJECTS = \
"CMakeFiles/LargeSchemaTests.dir/test/LargeSchemaTests.cc.o"

# External object files for target LargeSchemaTests
LargeSchemaTests_EXTERNAL_OBJECTS =

LargeSchemaTests: CMakeFiles/LargeSchemaTests.dir/test/LargeSchemaTests.cc.o
LargeSchemaTests: CMakeFiles/LargeSchemaTests.dir/build.make
LargeSchemaTests: libavrocpp.so.1.8.0.0
LargeSchemaTests: /usr/lib/x86_64-linux-gnu/libboost_filesystem.so
LargeSchemaTests: /usr/lib/x86_64-linux-gnu/libboost_system.so
LargeSchemaTests: /usr/lib/x86_64-linux-gnu/libboost_program_options.so
LargeSchemaTests: /usr/lib/x86_64-linux-gnu/libboost_iostreams.so
LargeSchemaTests: CMakeFiles/LargeSchemaTests.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --red --bold "Linking CXX executable LargeSchemaTests"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/LargeSchemaTests.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/LargeSchemaTests.dir/build: LargeSchemaTests
.PHONY : CMakeFiles/LargeSchemaTests.dir/build

CMakeFiles/LargeSchemaTests.dir/requires: CMakeFiles/LargeSchemaTests.dir/test/LargeSchemaTests.cc.o.requires
.PHONY : CMakeFiles/LargeSchemaTests.dir/requires

CMakeFiles/LargeSchemaTests.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/LargeSchemaTests.dir/cmake_clean.cmake
.PHONY : CMakeFiles/LargeSchemaTests.dir/clean

CMakeFiles/LargeSchemaTests.dir/depend:
	cd /home/yfy/iut/benchmarks/avro-cpp-1.8.0 && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/yfy/iut/benchmarks/avro-cpp-1.8.0 /home/yfy/iut/benchmarks/avro-cpp-1.8.0 /home/yfy/iut/benchmarks/avro-cpp-1.8.0 /home/yfy/iut/benchmarks/avro-cpp-1.8.0 /home/yfy/iut/benchmarks/avro-cpp-1.8.0/CMakeFiles/LargeSchemaTests.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/LargeSchemaTests.dir/depend
