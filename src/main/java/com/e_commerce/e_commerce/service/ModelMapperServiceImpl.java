package com.e_commerce.e_commerce.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;


    @Service
    public class ModelMapperServiceImpl implements ModelMapperService {
        private ModelMapper modelMapper;

        @Autowired
        public ModelMapperServiceImpl(ModelMapper modelMapper) {
            this.modelMapper = modelMapper;
        }

        @Override
        public ModelMapper forResponse() {
            modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE);
            return modelMapper;
        }

        @Override
        public ModelMapper forRequest() {
            modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STANDARD);
            return modelMapper;
        }
}
