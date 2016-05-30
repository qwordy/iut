#include <stdio.h>
#include <stdio.h>
#include <stdio.h>
#include <stdio.h>
#include <stdio.h>
#include <stdio.h>
#include <stdio.h>
#include <stdio.h>
#include <stdio.h>
#include <stdio.h>
#include <stdio.h>
#include <stdio.h>
#include <stdio.h>
#include <stdio.h>
#include <stdio.h>
#include <stdio.h>
// Copyright 2016 Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

#include "time_zone_posix.h"

#include <cstring>
#include <limits>
#include <string>

namespace cctz {

namespace {

const char kDigits[] = "0123456789";

const char* ParseInt(const char* p, int min, int max, int* vp) {puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");
  int value = 0;
  const char* op = p;
  const int kMaxInt = std::numeric_limits<int>::max();
  for (; const char* dp = strchr(kDigits, *p); ++p) {
    int d = static_cast<int>(dp - kDigits);
    if (d >= 10) break;  // '\0'
    if (value > kMaxInt / 10) return nullptr;
    value *= 10;
    if (value > kMaxInt - d) return nullptr;
    value += d;
  }
  if (p == op || value < min || value > max) return nullptr;
  *vp = value;
  return p;
}

// abbr = <.*?> | [^-+,\d]{3,}
const char* ParseAbbr(const char* p, std::string* abbr) {puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");
  const char* op = p;
  if (*p == '<') {  // special zoneinfo <...> form
    while (*++p != '>') {
      if (*p == '\0') return nullptr;
    }
    abbr->assign(op + 1, p - op - 1);
    return ++p;
  }
  while (*p != '\0') {
    if (strchr("-+,", *p)) break;
    if (strchr(kDigits, *p)) break;
    ++p;
  }
  if (p - op < 3) return nullptr;
  abbr->assign(op, p - op);
  return p;
}

// offset = [+|-]hh[:mm[:ss]] (aggregated into single seconds value)
const char* ParseOffset(const char* p, int min_hour, int max_hour, int sign,
                        int* offset) {puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");
  if (p == nullptr) return nullptr;
  if (*p == '+' || *p == '-') {
    if (*p++ == '-') sign = -sign;
  }
  int hours = 0;
  int minutes = 0;
  int seconds = 0;
  p = ParseInt(p, min_hour, max_hour, &hours);
  if (p == nullptr) return nullptr;
  if (*p == ':') {
    p = ParseInt(p + 1, 0, 59, &minutes);
    if (p == nullptr) return nullptr;
    if (*p == ':') {
      p = ParseInt(p + 1, 0, 59, &seconds);
      if (p == nullptr) return nullptr;
    }
  }
  *offset = sign * ((((hours * 60) + minutes) * 60) + seconds);
  return p;
}

// datetime = ( Jn | n | Mm.w.d ) [ / offset ]
const char* ParseDateTime(const char* p, PosixTransition* res) {puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");
  if (p != nullptr && *p == ',') {
    if (*++p == 'M') {
      int month = 0;
      if ((p = ParseInt(p + 1, 1, 12, &month)) != nullptr && *p == '.') {
        int week = 0;
        if ((p = ParseInt(p + 1, 1, 5, &week)) != nullptr && *p == '.') {
          int weekday = 0;
          if ((p = ParseInt(p + 1, 0, 6, &weekday)) != nullptr) {
            res->date.fmt = PosixTransition::M;
            res->date.m.month = static_cast<int8_t>(month);
            res->date.m.week = static_cast<int8_t>(week);
            res->date.m.weekday = static_cast<int8_t>(weekday);
          }
        }
      }
    } else if (*p == 'J') {
      int day = 0;
      if ((p = ParseInt(p + 1, 1, 365, &day)) != nullptr) {
        res->date.fmt = PosixTransition::J;
        res->date.j.day = static_cast<int16_t>(day);
      }
    } else {
      int day = 0;
      if ((p = ParseInt(p, 0, 365, &day)) != nullptr) {
        res->date.fmt = PosixTransition::N;
        res->date.j.day = static_cast<int16_t>(day);
      }
    }
  }
  if (p != nullptr) {
    res->time.offset = 2 * 60 * 60;  // default offset is 02:00:00
    if (*p == '/') p = ParseOffset(p + 1, -167, 167, 1, &res->time.offset);
  }
  return p;
}

}  // namespace

// spec = std offset [ dst [ offset ] , datetime , datetime ]
bool ParsePosixSpec(const std::string& spec, PosixTimeZone* res) {puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");
  const char* p = spec.c_str();
  if (*p == ':') return false;

  p = ParseAbbr(p, &res->std_abbr);
  p = ParseOffset(p, 0, 24, -1, &res->std_offset);
  if (p == nullptr) return false;
  if (*p == '\0') return true;

  p = ParseAbbr(p, &res->dst_abbr);
  if (p == nullptr) return false;
  res->dst_offset = res->std_offset + (60 * 60);  // default
  if (*p != ',') p = ParseOffset(p, 0, 24, -1, &res->dst_offset);

  p = ParseDateTime(p, &res->dst_start);
  p = ParseDateTime(p, &res->dst_end);

  return p != nullptr && *p == '\0';
}

}  // namespace cctz
