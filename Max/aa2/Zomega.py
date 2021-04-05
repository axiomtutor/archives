#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri Feb 12 10:19:36 2021

@author: addem
"""
from sympy import re, im, I, sqrt

omega = (-1+sqrt(3)*I)/2

def norm(x):
    return ((re(x))**2+(im(x))**2).evalf()

dd = 8-omega
d = 2-omega

nd = norm(d)

print("Norm of the divisor")
print(norm(d))
print()

for a in range(-10,10):
    for b in range(-10,10):
        quotient = a+b*omega
        remainder = dd - quotient*d
        nrem = norm(remainder)
        if nrem < norm(d): 
            print(a,b)
            print(norm(remainder))
            print()