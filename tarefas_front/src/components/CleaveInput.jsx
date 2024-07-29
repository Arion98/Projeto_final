import React, { useEffect, useRef } from 'react';
import Cleave from 'cleave.js';

const CleaveInput = ({ value, onChange, ...props }) => {
  const inputRef = useRef(null);

  useEffect(() => {
    if (inputRef.current) {
      new Cleave(inputRef.current, {
        numeral: true,
        numeralThousandsGroupStyle: 'thousand',
        prefix: 'R$ ',
        noImmediatePrefix: true,
        delimiter: '.',
        decimalSeparator: ',',
        numeralDecimalScale: 2,
        numeralIntegerScale: 7, // Permite até 9999999
      });
    }
  }, []);

  const handleChange = (e) => {
    // Remove prefix and format the value
    const rawValue = e.target.value.replace('R$ ', '').replace(/\./g, '').replace(',', '.');
    if (onChange) {
      onChange(rawValue);
    }
  };

  return (
    <input
      ref={inputRef}
      value={value ? `R$ ${parseFloat(value).toFixed(2).replace(/\d(?=(?:\d{3})+(?!\d))/g, '$&,').replace('.', ',')}` : ''}
      onChange={handleChange}
      {...props}
    />
  );
};

export default CleaveInput;
